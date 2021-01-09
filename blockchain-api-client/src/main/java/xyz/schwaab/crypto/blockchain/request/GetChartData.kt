package xyz.schwaab.crypto.blockchain.request

import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.SerializationException
import retrofit2.Response
import xyz.schwaab.crypto.blockchain.BlockchainInterface
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO
import xyz.schwaab.crypto.blockchain.model.ChartTypeDTO
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GetChartData(private val blockchainInterface: BlockchainInterface) {

    @Suppress("SimpleDateFormat") // Locale is not needed when formatting ISO date
    operator fun invoke(queryParameters: Parameters): Observable<Result> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedStartDate = dateFormat.format(queryParameters.startDate)
        val chartTypeSerialName = queryParameters.chartTypeDTO.serializedName

        return blockchainInterface.getChartData(chartTypeSerialName, formattedStartDate)
            .map {
                handleResponse(it)
            }
            .onErrorReturn {
                handleConnectionFailure(it)
            }
    }

    private fun handleConnectionFailure(throwable: Throwable): Result.Failure = when (throwable) {
        is IOException -> Result.Failure.LackOfConnection
        is SerializationException -> Result.Failure.ServiceUnavailable
        else -> Result.Failure.Unknown(throwable)
    }

    private fun handleResponse(it: Response<ChartDataDTO>): Result =
        if (it.isSuccessful) {
            Result.Success(it.body()!!)
        } else {
            Result.Failure.ServiceUnavailable
        }

    data class Parameters(
        val startDate: Date,
        val chartTypeDTO: ChartTypeDTO
    )

    sealed class Result {
        class Success(val chartDataDTO: ChartDataDTO) : Result()
        sealed class Failure : Result() {
            object ServiceUnavailable : Failure()
            object LackOfConnection : Failure()
            class Unknown(val throwable: Throwable) : Failure()
        }
    }
}
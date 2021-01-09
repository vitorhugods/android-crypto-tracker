package xyz.schwaab.crypto.blockchain.request

import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.SerializationException
import retrofit2.Response
import xyz.schwaab.crypto.blockchain.BlockchainInterface
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO
import xyz.schwaab.crypto.blockchain.model.ChartTypeDTO
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface GetChartData {
    operator fun invoke(queryParameters: Parameters): Observable<Result>

    data class Parameters(
        val startDate: LocalDate,
        val chartTypeDTO: ChartTypeDTO
    )

    sealed class Result {
        class Success(val chartDataDTO: ChartDataDTO) : Result()
        sealed class Failure : Result() {
            object BadRequest : Failure()
            object ServiceUnavailable : Failure()
            object LackOfConnection : Failure()
            class Unknown(val throwable: Throwable) : Failure()
        }
    }
}

internal class GetChartDataImpl(private val blockchainInterface: BlockchainInterface) :
    GetChartData {

    @Suppress("SimpleDateFormat") // Locale is not needed when formatting ISO date
    override operator fun invoke(queryParameters: GetChartData.Parameters): Observable<GetChartData.Result> {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
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

    private fun handleConnectionFailure(throwable: Throwable): GetChartData.Result.Failure =
        when (throwable) {
            is IOException -> GetChartData.Result.Failure.LackOfConnection
            is SerializationException -> GetChartData.Result.Failure.ServiceUnavailable
            else -> GetChartData.Result.Failure.Unknown(throwable)
        }

    private fun handleResponse(it: Response<ChartDataDTO>): GetChartData.Result =
        if (it.isSuccessful) {
            GetChartData.Result.Success(it.body()!!)
        } else {
            GetChartData.Result.Failure.ServiceUnavailable
        }
}
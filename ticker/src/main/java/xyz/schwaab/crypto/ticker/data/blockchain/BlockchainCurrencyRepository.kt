package xyz.schwaab.crypto.ticker.data.blockchain

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import xyz.schwaab.crypto.blockchain.BlockchainClient
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO
import xyz.schwaab.crypto.blockchain.model.ChartTypeDTO
import xyz.schwaab.crypto.blockchain.request.GetChartData
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.data.CurrencyRepository
import xyz.schwaab.crypto.ticker.domain.RequestChartData
import xyz.schwaab.crypto.ticker.domain.RequestChartData.Result
import xyz.schwaab.crypto.ticker.domain.RequestChartData.Result.Failure
import xyz.schwaab.crypto.ticker.domain.RequestChartData.Result.Success
import java.time.LocalDate

class BlockchainCurrencyRepository(private val blockchainClient: BlockchainClient) :
    CurrencyRepository {

    override fun getChartData(parameters: RequestChartData.Parameters): Single<Result> {
        val queryParameters = parameters.toQueryParameters()

        return blockchainClient.getChartData(queryParameters)
            .map(::mapResult)
            .singleOrError()
            .subscribeOn(Schedulers.computation())
    }

    private fun mapResult(result: GetChartData.Result) = when (result) {
        is GetChartData.Result.Success -> result.toModel()

        GetChartData.Result.Failure.ServiceUnavailable,
        GetChartData.Result.Failure.BadRequest -> Failure.ServiceUnavailable

        GetChartData.Result.Failure.LackOfConnection -> Failure.LackOfConnection

        is GetChartData.Result.Failure.Unknown -> Failure.UnknownError(result.throwable)
    }

    private fun GetChartData.Result.Success.toModel(): Success {
        val chartData = ChartData(chartDataDTO.unit, chartDataDTO.values.toModel())
        return Success(chartData)
    }

    private fun List<ChartDataDTO.DataPoint>.toModel(): List<ChartData.DataPoint> {
        return map {
            ChartData.DataPoint(it.x, it.y)
        }
    }

    private fun RequestChartData.Parameters.toQueryParameters() =
        GetChartData.Parameters(startDate, chartTypeDTO)

    private val RequestChartData.Parameters.startDate: LocalDate
        get() = LocalDate.now().minusDays(sinceDaysAgo.toLong())

    private val RequestChartData.Parameters.chartTypeDTO: ChartTypeDTO
        get() = when (this.chartType) {
            ChartType.MARKET_PRICE -> ChartTypeDTO.MARKET_PRICE
            ChartType.TRANSACTIONS_PER_SECOND -> ChartTypeDTO.TRANSACTIONS_PER_SECOND
        }
}
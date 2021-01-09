package xyz.schwaab.crypto.ticker.domain

import io.reactivex.rxjava3.core.Single
import xyz.schwaab.crypto.base.domain.RequestInteractor
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.data.CurrencyRepository

interface RequestChartData :
    RequestInteractor<RequestChartData.Parameters, RequestChartData.Result> {

    data class Parameters(
        val chartType: ChartType,
        val sinceDaysAgo: Int
    )

    sealed class Result {
        class Success(val chartData: ChartData) : Result()
        sealed class Failure : Result() {
            object LackOfConnection : Failure()
            object ServiceUnavailable : Failure()
            class UnknownError(val throwable: Throwable) : Failure()
        }
    }
}

internal class RequestChartDataImpl(private val currencyRepository: CurrencyRepository) :
    RequestChartData {

    override fun invoke(params: RequestChartData.Parameters): Single<RequestChartData.Result> {
        return currencyRepository.getChartData(params)
    }
}
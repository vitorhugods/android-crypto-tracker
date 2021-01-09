package xyz.schwaab.crypto.ticker.domain

import io.reactivex.rxjava3.core.Single
import xyz.schwaab.crypto.base.domain.RequestInteractor
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.data.CurrencyRepository

class RequestChartData(private val currencyRepository: CurrencyRepository) :
    RequestInteractor<RequestChartData.Parameters, RequestChartData.Result> {

    override fun invoke(params: Parameters): Single<Result> {
        return currencyRepository.getChartData(params)
    }

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
package xyz.schwaab.crypto.ticker.data

import io.reactivex.rxjava3.core.Single
import xyz.schwaab.crypto.ticker.domain.RequestChartData

interface CurrencyRepository {

    fun getChartData(parameters: RequestChartData.Parameters): Single<RequestChartData.Result>
}
package xyz.schwaab.crypto.ticker.domain

import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factory { RequestChartDataImpl(get()) } bind RequestChartData::class
}
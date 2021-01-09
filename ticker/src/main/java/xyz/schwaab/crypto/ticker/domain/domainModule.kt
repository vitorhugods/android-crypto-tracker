package xyz.schwaab.crypto.ticker.domain

import org.koin.dsl.module

val domainModule = module {
    factory { RequestChartData(get()) }
}
package xyz.schwaab.crypto.ticker.presentation

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.bind
import org.koin.dsl.module
import xyz.schwaab.crypto.ticker.domain.RequestChartData
import xyz.schwaab.crypto.ticker.domain.RequestChartDataImpl

val presentationModule = module {
    factory { DateAxisFormatter(get()) }
    factory { mapToDisplayableChartData }
    viewModel { CurrencyTickerViewModel(get(), get()) }
    fragment { CurrencyTickerFragment(get()) }
}
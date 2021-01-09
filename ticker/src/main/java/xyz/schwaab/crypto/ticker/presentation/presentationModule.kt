package xyz.schwaab.crypto.ticker.presentation

import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val presentationModule = module {
    fragment { CurrencyTickerFragment() }
}
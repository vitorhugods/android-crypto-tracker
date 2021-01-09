package xyz.schwaab.crypto.ticker

import org.koin.core.module.Module
import xyz.schwaab.crypto.ticker.data.dataModule
import xyz.schwaab.crypto.ticker.domain.domainModule
import xyz.schwaab.crypto.ticker.presentation.presentationModule

object TickerModules {
    val list: List<Module> = listOf(dataModule, domainModule, presentationModule)
}
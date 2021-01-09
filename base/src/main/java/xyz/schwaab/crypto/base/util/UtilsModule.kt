package xyz.schwaab.crypto.base.util

import org.koin.dsl.bind
import org.koin.dsl.module
import xyz.schwaab.crypto.base.presentation.DateFormatter
import xyz.schwaab.crypto.base.presentation.SimpleDateFormatter

val utilsModule = module {
    factory { SystemLocaleProvider() } bind UserLocaleProvider::class
    factory { SimpleDateFormatter(get()) } bind DateFormatter::class
}
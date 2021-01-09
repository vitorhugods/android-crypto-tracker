package xyz.schwaab.crypto.ticker.data

import org.koin.dsl.bind
import org.koin.dsl.module
import xyz.schwaab.crypto.base.data.OkHttpClientProvider
import xyz.schwaab.crypto.blockchain.BlockchainClient
import xyz.schwaab.crypto.ticker.data.blockchain.BlockchainCurrencyRepository

val dataModule = module {
    val okHttpClient = OkHttpClientProvider.default().client.newBuilder().build()
    single { BlockchainClient.create(okHttpClient) }
    factory { BlockchainCurrencyRepository(get()) } bind CurrencyRepository::class
}
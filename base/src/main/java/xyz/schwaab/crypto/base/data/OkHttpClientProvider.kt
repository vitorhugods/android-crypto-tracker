package xyz.schwaab.crypto.base.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import xyz.schwaab.crypto.base.BuildConfig

interface OkHttpClientProvider {
    val client: OkHttpClient

    companion object {
        fun default(): OkHttpClientProvider = DefaultOkHttpClientProvider
    }
}

internal object DefaultOkHttpClientProvider : OkHttpClientProvider {
    override val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }
}
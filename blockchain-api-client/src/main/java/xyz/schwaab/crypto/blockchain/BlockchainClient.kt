package xyz.schwaab.crypto.blockchain

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import xyz.schwaab.crypto.blockchain.request.GetChartData
import xyz.schwaab.crypto.blockchain.request.GetChartDataImpl

interface BlockchainClient {
    val getChartData: GetChartData

    companion object {
        private const val BASE_URL = "https://api.blockchain.info/"

        fun create(okHttpClient: OkHttpClient): BlockchainClient {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(createJsonAdapter())
                .build()

            val blockchainInterface = retrofit.create(BlockchainInterface::class.java)

            return HttpBlockchainClient(blockchainInterface)
        }

        private fun createJsonAdapter(): Converter.Factory {
            val mediaType = "application/json".toMediaType()

            val jsonConfiguration = Json {
                ignoreUnknownKeys = true
            }

            @Suppress("EXPERIMENTAL_API_USAGE")
            return jsonConfiguration.asConverterFactory(mediaType)
        }
    }
}

class HttpBlockchainClient internal constructor(blockchainInterface: BlockchainInterface) :
    BlockchainClient {

    override val getChartData: GetChartData = GetChartDataImpl(blockchainInterface)
}
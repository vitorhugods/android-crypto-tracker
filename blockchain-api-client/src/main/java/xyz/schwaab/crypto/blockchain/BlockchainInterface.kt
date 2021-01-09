package xyz.schwaab.crypto.blockchain

import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO

interface BlockchainInterface {

    //TODO: Test for endpoint path/parameters/body using MockWebServer
    @GET("charts/{chartname}")
    fun getChartData(
        @Path("chartname") chartName: String,
        @Query("start") statDate: String
    ): Observable<Response<ChartDataDTO>>

}

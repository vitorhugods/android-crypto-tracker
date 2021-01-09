package xyz.schwaab.crypto.blockchain.request

import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.SerializationException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import xyz.schwaab.crypto.blockchain.BlockchainInterface
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO
import xyz.schwaab.crypto.blockchain.model.ChartTypeDTO
import xyz.schwaab.crypto.blockchain.util.createDate
import java.net.SocketTimeoutException
import java.time.LocalDate
import java.util.*

class GetChartDataTest {

    private lateinit var mockInterface: BlockchainInterface
    private lateinit var subject: GetChartData

    @Before
    fun setUp() {
        mockInterface = mock(BlockchainInterface::class)
        subject = GetChartDataImpl(mockInterface)
    }

    @Test
    fun shouldCallTheInterfaceWithCorrectParameters() {
        When calling mockInterface.getChartData(any(), any()) itReturns Observable.just(
            Response.success(
                ChartDataDTO("", listOf())
            )
        )

        val date = createDate(2021, 1, 7)

        subject.invoke(GetChartData.Parameters(date, ChartTypeDTO.MARKET_PRICE))

        Verify on mockInterface that mockInterface.getChartData(
            ChartTypeDTO.MARKET_PRICE.serializedName,
            "2021-01-07"
        )
    }

    @Test
    fun successWhenResponseIsSuccessful() {
        val expectedResultData = ChartDataDTO(
            "Lorem ipsum", listOf(
                ChartDataDTO.DataPoint(42L, 101.5)
            )
        )

        When calling mockInterface.getChartData(any(), any()) itReturns Observable.just(
            Response.success(expectedResultData)
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Success && it.chartDataDTO == expectedResultData
            }
    }

    @Test
    fun serviceUnavailableWhenResponseStatusCodeIs500() {
        When calling mockInterface.getChartData(any(), any()) itReturns Observable.just(
            Response.error(500, "".toResponseBody())
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Failure.ServiceUnavailable
            }
    }

    @Test
    fun serviceUnavailableWhenResponseStatusCodeIs400() {
        When calling mockInterface.getChartData(any(), any()) itReturns Observable.just(
            Response.error(400, "".toResponseBody())
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Failure.BadRequest
            }
    }

    @Test
    fun serviceUnavailableWhenFailingToDeserializeResponse() {
        When calling mockInterface.getChartData(any(), any()) itReturns Observable.error(
            SerializationException()
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Failure.ServiceUnavailable
            }
    }

    @Test
    fun lackOfConnectionWhenFailingToEstablishConnection() {
        When calling mockInterface.getChartData(any(), any()) itReturns Observable.error(
            SocketTimeoutException()
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Failure.LackOfConnection
            }
    }

    @Test
    fun unknownFailureWhenGenericErrorOccurs() {
        val thrown = Throwable()

        When calling mockInterface.getChartData(any(), any()) itReturns Observable.error(
            thrown
        )

        subject.invoke(createParameters())
            .test()
            .assertValue {
                it is GetChartData.Result.Failure.Unknown
                        && it.throwable == thrown
            }
    }

    private fun createParameters() =
        GetChartData.Parameters(
            LocalDate.of(2020,10,1),
            ChartTypeDTO.TRANSACTIONS_PER_SECOND
        )
}
package xyz.schwaab.crypto.ticker.data.blockchain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import xyz.schwaab.crypto.blockchain.BlockchainClient
import xyz.schwaab.crypto.blockchain.model.ChartDataDTO
import xyz.schwaab.crypto.blockchain.model.ChartTypeDTO
import xyz.schwaab.crypto.blockchain.request.GetChartData
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.domain.RequestChartData
import java.time.LocalDate
import java.util.concurrent.TimeUnit

class BlockchainCurrencyRepositoryTest {

    lateinit var mockClient: BlockchainClient
    lateinit var mockGetChartData: GetChartData
    lateinit var subject: BlockchainCurrencyRepository

    @Before
    fun setUp() {
        mockGetChartData = mock()
        mockClient = mock()
        When calling mockClient.getChartData itReturns mockGetChartData

        subject = BlockchainCurrencyRepository(mockClient)
    }

    @Test
    fun shouldUseTheChartDataTaskWhenGettingChartData() {
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.ServiceUnavailable
        )

        subject.getChartData(createChartDataParameters())

        Verify on mockGetChartData that mockGetChartData.invoke(any()) was called
    }

    @Test
    fun shouldCallTheChartDataTaskWithTheRightParameters() {
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.BadRequest
        )

        val params = createChartDataParameters().copy(
            chartType = ChartType.MARKET_PRICE,
            sinceDaysAgo = 10
        )

        subject.getChartData(params)

        val expectedCallParams = GetChartData.Parameters(
            LocalDate.now().minusDays(10),
            ChartTypeDTO.MARKET_PRICE
        )

        Verify on mockGetChartData that mockGetChartData.invoke(expectedCallParams) was called
    }

    @Test
    fun dataPointsShouldBeMappedCorrectly() {
        val chartDataDTO = ChartDataDTO("Metres", listOf(ChartDataDTO.DataPoint(42L, 12.3)))
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Success(chartDataDTO)
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Success
                        && result.chartData.dataPoints == listOf(ChartData.DataPoint(42L, 12.3))
            }
    }

    @Test
    fun unitShouldBeMappedCorrectly() {
        val chartDataDTO = ChartDataDTO("Metres", listOf(ChartDataDTO.DataPoint(42L, 12.3)))
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Success(chartDataDTO)
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Success
                        && result.chartData.unit == "Metres"
            }
    }

    @Test
    fun serviceUnavailableShouldBeReturnedWhenFacingBadRequest() {
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.BadRequest
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Failure.ServiceUnavailable
            }
    }

    @Test
    fun lackOfConnectionShouldBeReturnedWhenFacingConnectionIssues() {
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.LackOfConnection
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Failure.LackOfConnection
            }
    }

    @Test
    fun serviceUnavailableShouldBeReturnedWhenServiceIsDown() {
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.ServiceUnavailable
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Failure.ServiceUnavailable
            }
    }

    @Test
    fun unknownThrowableShouldBeForwarded() {
        val thrown = Throwable()
        When calling mockGetChartData(any()) itReturns Observable.just(
            GetChartData.Result.Failure.Unknown(thrown)
        )

        subject.getChartData(createChartDataParameters())
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue { result ->
                result is RequestChartData.Result.Failure.UnknownError
                        && result.throwable == thrown
            }
    }

    private fun createChartDataParameters() = RequestChartData.Parameters(ChartType.MARKET_PRICE, 2)
}
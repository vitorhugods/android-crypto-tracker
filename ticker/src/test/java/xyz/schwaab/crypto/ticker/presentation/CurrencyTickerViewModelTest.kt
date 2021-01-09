package xyz.schwaab.crypto.ticker.presentation

import com.jraska.livedata.test
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import xyz.schwaab.crypto.base.test.BaseViewModelTest
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.domain.RequestChartData

class CurrencyTickerViewModelTest : BaseViewModelTest() {

    lateinit var subject: CurrencyTickerViewModel
    lateinit var mockChartDataRequest: RequestChartData
    val mappingFunction = mapToDisplayableChartData

    @Before
    fun setUp() {
        mockChartDataRequest = mock()
        subject = CurrencyTickerViewModel(mockChartDataRequest, mappingFunction)
    }

    @Test
    fun shouldCallRequestChartDataWhenChangingChartPeriod() {
        When calling mockChartDataRequest(any()) itReturns Single.just(RequestChartData.Result.Failure.ServiceUnavailable)

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.WEEK)

        Verify on mockChartDataRequest that mockChartDataRequest(any()) was called
    }

    @Test
    fun shouldCallRequestChartDataWithTheRightParametersWhenSelectingAChartPeriod() {
        When calling mockChartDataRequest(any()) itReturns Single.just(RequestChartData.Result.Failure.ServiceUnavailable)

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.MONTH)

        Verify on mockChartDataRequest that mockChartDataRequest(
            RequestChartData.Parameters(
                ChartType.MARKET_PRICE, CurrencyTickerViewModel.ChartPeriod.MONTH.amountOfDays
            )
        ) was called
    }

    @Test
    fun shouldUpdateChartDataAfterSelectingAChartPeriod() {
        val originalChartData = ChartData("Lorem Ipsum", listOf())
        val testObserver = subject.displayableChartData.test()
        When calling mockChartDataRequest(any()) itReturns Single.just(
            RequestChartData.Result.Success(originalChartData)
        )

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.YEAR)

        testObserver.awaitValue()
            .value() shouldBeEqualTo mappingFunction(originalChartData)
    }

    @Test
    fun shouldStartWithPeriodSelectionOfOneWeek() {
        val testObserver = subject.selectedChartPeriod.test()
        testObserver.value() shouldBeEqualTo CurrencyTickerViewModel.ChartPeriod.WEEK
    }

    @Test
    fun shouldUpdateSelectedChartPeriod() {
        val testObserver = subject.selectedChartPeriod.test()
        When calling mockChartDataRequest(any()) itReturns Single.just(
            RequestChartData.Result.Failure.ServiceUnavailable
        )

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.MONTH)

        testObserver.value() shouldBeEqualTo CurrencyTickerViewModel.ChartPeriod.MONTH
    }

    @Test
    fun shouldStartWithoutErrors() {
        val testObserver = subject.errorEvent.test()

        testObserver.assertNoValue()
    }

    @Test
    fun shouldForwardConnectionErrors() {
        val testObserver = subject.errorEvent.test()

        When calling mockChartDataRequest(any()) itReturns Single.just(
            RequestChartData.Result.Failure.LackOfConnection
        )

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.WEEK)

        testObserver.value() shouldBeEqualTo TickerUserJourneyErrors.LACK_OF_CONNECTION
    }

    @Test
    fun shouldForwardServiceUnavailability() {
        val testObserver = subject.errorEvent.test()

        When calling mockChartDataRequest(any()) itReturns Single.just(
            RequestChartData.Result.Failure.ServiceUnavailable
        )

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.WEEK)

        testObserver.value() shouldBeEqualTo TickerUserJourneyErrors.SERVICE_UNAVAILABLE
    }

    @Test
    fun shouldForwardUnknownErrors() {
        val testObserver = subject.errorEvent.test()

        When calling mockChartDataRequest(any()) itReturns Single.just(
            RequestChartData.Result.Failure.UnknownError(Throwable())
        )

        subject.onChartPeriodSelectionChanged(CurrencyTickerViewModel.ChartPeriod.WEEK)

        testObserver.value() shouldBeEqualTo TickerUserJourneyErrors.UNKNOWN
    }
}
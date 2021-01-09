package xyz.schwaab.crypto.ticker.domain

import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.data.CurrencyRepository

class RequestChartDataTest {

    lateinit var subject: RequestChartData
    lateinit var mockRepository: CurrencyRepository

    @Before
    fun setup() {
        mockRepository = mock()
        subject = RequestChartData(mockRepository)
    }

    @Test
    fun shouldCallTheRepositoryToGetChartData() {
        When calling mockRepository.getChartData(any()) itReturns Single.error(Throwable())

        subject(createParameters())

        Verify on mockRepository that mockRepository.getChartData(any()) was called
    }

    @Test
    fun shouldCallTheRepositoryWithTheRightParameters() {
        When calling mockRepository.getChartData(any()) itReturns Single.error(Throwable())

        val parameters = createParameters().copy(
            sinceDaysAgo = 23
        )

        subject(parameters)

        Verify on mockRepository that mockRepository.getChartData(parameters) was called
    }

    private fun createParameters() =
        RequestChartData.Parameters(ChartType.TRANSACTIONS_PER_SECOND, 8)
}
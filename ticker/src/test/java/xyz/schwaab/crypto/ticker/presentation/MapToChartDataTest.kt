package xyz.schwaab.crypto.ticker.presentation

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainSame
import org.junit.Test
import xyz.schwaab.crypto.ticker.data.ChartData

class MapToChartDataTest {

    val subject = mapToDisplayableChartData

    @Test
    fun shouldMapUnitProperly() {
        val expectedUnit = "Lorem Ipsum"
        val initialData = ChartData(expectedUnit, listOf())

        val mapped = subject(initialData)

        mapped.unit shouldBeEqualTo expectedUnit
    }

    @Test
    fun shouldMapDataPointsProperly() {
        val originalDataPoint = ChartData.DataPoint(42L, 42.0)
        val initialData = ChartData("", listOf(originalDataPoint))

        val mapped = subject(initialData)

        mapped.dataPoints shouldContainSame listOf(
            DisplayableChartData.DisplayableDataPoint(originalDataPoint.x, originalDataPoint.y)
        )
    }
}
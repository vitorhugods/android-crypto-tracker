package xyz.schwaab.crypto.ticker.presentation

import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class LineDataUtilsTest {

    @Test
    fun shouldMapToASingleDataSet() {
        val originalDataPoint = DisplayableChartData.DisplayableDataPoint(42L, 1.0)
        val chartData = DisplayableChartData("", listOf(originalDataPoint))

        val lineData = chartData.getLineData()

        lineData.dataSetCount shouldBeEqualTo 1
    }

    @Test
    fun shouldMapDataPointsCorrectly() {
        val originalDataPoint = DisplayableChartData.DisplayableDataPoint(42L, 1.0)
        val chartData = DisplayableChartData("", listOf(originalDataPoint))

        val lineData = chartData.getLineData()

        val dataSet = lineData.getDataSetByIndex(0)
        dataSet.entryCount shouldBeEqualTo 1

        val entry = dataSet.getEntryForIndex(0)
        entry.x shouldBeEqualTo originalDataPoint.x.toFloat()
        entry.y shouldBeEqualTo originalDataPoint.y.toFloat()
    }

    @Test
    fun shouldMapLabelCorrectly() {
        val originalLabel = "Lorem Ipsum"
        val chartData =
            DisplayableChartData(
                originalLabel,
                listOf(DisplayableChartData.DisplayableDataPoint(42L, 1.0))
            )

        val lineData = chartData.getLineData()

        val dataSet = lineData.getDataSetByIndex(0)
        dataSet.label shouldBeEqualTo originalLabel
    }
}
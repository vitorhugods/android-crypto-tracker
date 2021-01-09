package xyz.schwaab.crypto.ticker.presentation

import xyz.schwaab.crypto.ticker.data.ChartData

val mapToDisplayableChartData: ((ChartData) -> DisplayableChartData) = { chartData ->
    val dataPoints = chartData.dataPoints.map { dataPoint ->
        DisplayableChartData.DisplayableDataPoint(dataPoint.x, dataPoint.y)
    }
    DisplayableChartData(chartData.unit, dataPoints)
}
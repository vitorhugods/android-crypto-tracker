package xyz.schwaab.crypto.ticker.presentation

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

fun DisplayableChartData.getLineData(): LineData {
    val entries = dataPoints.map { dataPoint ->
        Entry(dataPoint.x.toFloat(), dataPoint.y.toFloat())
    }
    val lineDataSet = LineDataSet(entries, unit)
    return LineData(lineDataSet)
}
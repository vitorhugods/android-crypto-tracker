package xyz.schwaab.crypto.ticker.presentation

data class DisplayableChartData(
    val unit: String,
    val dataPoints: List<DisplayableDataPoint>
) {

    data class DisplayableDataPoint(
        val x: Long,
        val y: Double
    )
}
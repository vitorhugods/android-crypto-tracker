package xyz.schwaab.crypto.ticker.data

data class ChartData(
    val unit: String,
    val dataPoints: List<DataPoint>
){
    data class DataPoint(
        val x: Long,
        val y: Double
    )
}
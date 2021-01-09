package xyz.schwaab.crypto.blockchain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChartDataDTO(
    @SerialName("unit") val unit: String,
    @SerialName("values") val values: List<DataPoint>
) {
    @Serializable
    data class DataPoint(
        @SerialName("x") val x: Long,
        @SerialName("y") val y: Double
    )
}
package dev.ladibells.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQuality(
    val co: Double,
    val no2: Double,
    val o3: Double? = null,
    val so2: Double,
    @SerialName("pm2_5") val pm2_5: Double,
    val pm10: String,
    @SerialName("us-epa-index") val usEpaIndex: Int,
    @SerialName("gb-defra-index") val gbDefraIndex: Int
)
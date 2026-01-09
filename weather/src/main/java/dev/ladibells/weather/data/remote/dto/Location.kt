package dev.ladibells.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val country: String,
    val region: String,
    val lat: String,
    val lon: String,
    @SerialName("tz_id") val tzId: String,
    val localtime: String,
    @SerialName("localtime_epoch") val localtimeEpoch: Long,
    @SerialName("utc_offset") val utcOffset: String? = null
)
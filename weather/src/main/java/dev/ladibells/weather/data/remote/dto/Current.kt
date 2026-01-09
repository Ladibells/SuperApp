package dev.ladibells.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
@SerialName("last_updated_epoch") val lastUpdatedEpoch: Long,
@SerialName("last_updated") val lastUpdated: String? = null,
@SerialName("temp_c") val tempC: Double,
@SerialName("temp_f") val tempF: Double,
@SerialName("is_day") val isDay: Int,
val condition: Condition,
@SerialName("wind_mph") val windMph: Double,
@SerialName("wind_kph") val windKph: Double,
@SerialName("wind_degree") val windDegree: Int,
@SerialName("wind_dir") val windDir: String,
@SerialName("pressure_mb") val pressureMb: Double,
@SerialName("pressure_in") val pressureIn: Double,
@SerialName("precip_mm") val precipMm: Double,
@SerialName("precip_in") val precipIn: Double,
val humidity: Int,
val cloud: Int,
@SerialName("feelslike_c") val feelslikeC: Double,
@SerialName("feelslike_f") val feelslikeF: Double,
@SerialName("vis_km") val visKm: Double,
@SerialName("vis_miles") val visMiles: Double,
val uv: Double,
@SerialName("gust_mph") val gustMph: Double,
@SerialName("gust_kph") val gustKph: Double,
@SerialName("air_quality") val airQuality: AirQuality? = null,
)
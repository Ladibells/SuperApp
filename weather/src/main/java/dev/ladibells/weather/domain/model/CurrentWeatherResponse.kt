package dev.ladibells.weather.domain.model

data class CurrentWeatherResponse(
    val locationName: String,
    val temperatureC: Double,
    val weatherIcon: String?,
    val observationTime: String,
    val weatherDescription: String? = null,
    val airQuality03: Double,
    val feelslikeC: Double,
    val windSpeed: Double
)

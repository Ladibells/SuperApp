package dev.ladibells.weather.data.remote.dto

import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponseDto(
    val location: Location,
    val current: Current
)

fun CurrentWeatherResponseDto.toCurrentWeatherResponse(): CurrentWeatherResponse {
    return CurrentWeatherResponse(
        locationName = location.name,
        observationTime = current.lastUpdated ?: "",
        weatherIcon = "https:${current.condition.icon}",
        temperatureC = current.tempC,
        airQuality03 = current.airQuality?.o3 ?: 0.0,
        weatherDescription = current.condition.text,
        feelslikeC = current.feelslikeC,
        windSpeed = current.windKph
    )
}

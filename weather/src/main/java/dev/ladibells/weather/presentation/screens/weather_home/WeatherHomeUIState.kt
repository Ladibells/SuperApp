package dev.ladibells.weather.presentation.screens.weather_home

import dev.ladibells.weather.domain.model.CurrentWeatherResponse

data class WeatherHomeUIState(
    val isLoading: Boolean = false,
//    val currentWeatherResponse: CurrentWeatherResponse? = null,
    val error: String? = null,
    val locationName: String? = null,
    val temperature: String? = null,
    val weatherIcon: String? = null,
    val observationTime: String? = null,
    val airQuality03: String? = null,


    val forecastDayDate: String? = null,
    val maxTempC: Double? = null,
    val minTempC: Double? = null,
    val avgHumidity: String? = null,
    val summaryOfTheDay: String? = null,
    val summaryIconOfTheDay: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val moonrise: String? = null,
    val moonset: String? = null,
)

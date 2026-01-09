package dev.ladibells.weather.domain.repository

import dev.ladibells.utilities.Resource
import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import dev.ladibells.weather.domain.model.MarineWeatherForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(accessKey: String, query: String): Flow<Resource<CurrentWeatherResponse>>
    suspend fun getMarineWeatherForecast(accessKey: String, query: String): Flow<Resource<MarineWeatherForecast>>
}


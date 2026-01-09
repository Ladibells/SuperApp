package dev.ladibells.weather.data.remote

import dev.ladibells.weather.data.remote.dto.CurrentWeatherResponseDto
import dev.ladibells.weather.data.remote.dto.MarineWeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") accessKey: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String = "yes"
    ): CurrentWeatherResponseDto

    @GET("/v1/marine.json")
    suspend fun getMarineWeatherForecast(
        @Query("key") accessKey: String,
        @Query("q") query: String,
        @Query("days") days: Int = 1,
    ): MarineWeatherForecastDto
}


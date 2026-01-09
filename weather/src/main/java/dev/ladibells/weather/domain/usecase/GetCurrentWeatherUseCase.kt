package dev.ladibells.weather.domain.usecase

import dev.ladibells.utilities.Resource
import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import dev.ladibells.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(accessKey: String, query: String) : Flow<Resource<CurrentWeatherResponse>> {
        return repository.getCurrentWeather(accessKey, query)
    }
}
package dev.ladibells.weather.domain.usecase

import dev.ladibells.utilities.Resource
import dev.ladibells.weather.domain.model.MarineWeatherForecast
import dev.ladibells.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarineWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(accessKey: String, query: String): Flow<Resource<MarineWeatherForecast>> {
        return repository.getMarineWeatherForecast(accessKey = accessKey, query = query)

    }
}
package dev.ladibells.weather.data.repository

import androidx.lifecycle.ViewModel
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.constants.AppConstants
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.weather.data.remote.WeatherApi
import dev.ladibells.weather.data.remote.dto.toCurrentWeatherResponse
import dev.ladibells.weather.data.remote.dto.toMarineWeatherForecast
import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import dev.ladibells.weather.domain.model.MarineWeatherForecast
import dev.ladibells.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        accessKey: String,
        query: String
    ): Flow<Resource<CurrentWeatherResponse>> = flow {
        try {
            emit(Resource.Loading())
            val currentWeatherResponse = api.getCurrentWeather(accessKey, query)
            emit(Resource.Success(currentWeatherResponse.toCurrentWeatherResponse()))
            AppLogger.d(message = "Inside success of getCurrentWeather")
            AppLogger.d(tag = "WeatherRepositoryImpl", message = "getCurrentWeather: $currentWeatherResponse")
        } catch (e: HttpException) {
            AppLogger.d(message = "Inside error of HttpException in getCurrentWeather ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            AppLogger.d(message = "Inside error of IOException in getCurrentWeather ${e.localizedMessage}")
            emit(Resource.Error(AppConstants.IO_ERROR_MESSAGE))
        }

    }

    override suspend fun getMarineWeatherForecast(
        accessKey: String,
        query: String
    ): Flow<Resource<MarineWeatherForecast>> = flow {
        try {
            emit(Resource.Loading())
            val marineWeatherForecast = api.getMarineWeatherForecast(accessKey, query)
            emit(Resource.Success(marineWeatherForecast.toMarineWeatherForecast()))
            AppLogger.d(message = "Inside success of getMarineWeatherForecast")
            AppLogger.d(tag = "WeatherRepositoryImpl", message = "getMarineWeatherForecast: $marineWeatherForecast")
        } catch (e: HttpException) {
            AppLogger.d(message = "Inside error of HttpException in getMarineWeatherForecast ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            AppLogger.d(message = "Inside error of IOException in getMarineWeatherForecast ${e.localizedMessage}")
            emit(Resource.Error(AppConstants.IO_ERROR_MESSAGE))
        }
    }
}
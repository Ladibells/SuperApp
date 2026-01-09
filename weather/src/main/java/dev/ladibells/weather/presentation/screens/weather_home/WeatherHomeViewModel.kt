package dev.ladibells.weather.presentation.screens.weather_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.constants.AppConstants
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import dev.ladibells.weather.domain.usecase.GetCurrentWeatherUseCase
import dev.ladibells.weather.domain.usecase.GetMarineWeatherForecastUseCase
import dev.ladibells.weather.domain.usecase.GetUserLocationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getMarineWeatherForecastUseCase: GetMarineWeatherForecastUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeatherHomeUIState())
    val state: State<WeatherHomeUIState> = _state

    init {
        AppLogger.d(message = "Inside init of WeatherHomeViewModel")
        getUserLastLocationFromDB()
    }

    fun fetchCurrentLocationWeather(location: String) {
        AppLogger.d(message = "Inside fetchCurrentLocationWeather of WeatherHomeViewModel")
        viewModelScope.launch {
            getCurrentWeather(accessKey = AppConstants.WEATHER_ACCESS_KEY, query = location)
        }
    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Fetched UserLocationFromDB = $cityName")
            _state.value = _state.value.copy(locationName = cityName)
            fetchCurrentLocationWeather(location = cityName)
        }
    }

    private suspend fun getCurrentWeather(accessKey: String, query: String) {
        AppLogger.d(message = "Inside getCurrentWeather of WeatherHomeViewModel")
        getCurrentWeatherUseCase(accessKey, query).onEach { result ->
            AppLogger.d(message = "Inside onEach of getCurrentWeather of WeatherHomeViewModel")
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        locationName = result.data?.locationName,
                        temperature = "${ceil(result.data?.temperatureC ?: 0.0).toInt()}°C",
                        weatherIcon = result.data?.weatherIcon,
                        observationTime = result.data?.observationTime,
                        airQuality03 = ceil(result.data?.airQuality03 ?: 0.0).toInt().toString(),
                        error = null
                    )
                    AppLogger.d( message = "Success! getCurrentWeather Inside WeatherHomeViewModel : ${result.data}")
                    getMarineWeatherForecast(accessKey = accessKey, query = query)
                }
                is Resource.Error -> {
                    AppLogger.d( message = "Error! getCurrentWeather: ${result.message}")
                    _state.value = WeatherHomeUIState(
                        isLoading = false,
//                        currentWeatherResponse = null,
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                    AppLogger.d( message = "Loading! Inside getCurrentWeather")
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getMarineWeatherForecast(accessKey: String, query: String) {
        AppLogger.d(message = "Inside getMarineWeatherForecast of WeatherHomeViewModel")
        getMarineWeatherForecastUseCase(accessKey, query).onEach { result ->
            AppLogger.d(message = "Inside onEach of getMarineWeatherForecast of WeatherHomeViewModel")
            when (result) {
                is Resource.Error<*> -> {
                    AppLogger.d(message = "Error! getMarineWeatherForecast: ${result.message}")
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }

                is Resource.Loading<*> -> {
                    AppLogger.d(message = "Loading! Inside getMarineWeatherForecast")
                    _state.value = _state.value.copy(isLoading = true)
                }

                    is Resource.Success<*> -> {
                        AppLogger.d(message = "Success! getMarineWeatherForecast Inside WeatherHomeViewModel : ${result.data}")
                        _state.value = _state.value.copy(
                            isLoading = false,
                            forecastDayDate = result.data?.forecastDayDate,
                            maxTempC = result.data?.maxTempC,
                            minTempC = result.data?.minTempC,
                            avgHumidity = ceil(result.data?.avgHumidity ?: 0.0).toInt().toString(),
                            summaryOfTheDay = result.data?.summaryOfTheDay,
                            summaryIconOfTheDay = result.data?.summaryIconOfTheDay,
                            sunrise = result.data?.sunrise,
                            sunset = result.data?.sunset,
                            moonrise = result.data?.moonrise,
                            moonset = result.data?.moonset,
                            weatherIcon = result.data?.summaryIconOfTheDay,
                            error = null
                        )
                        AppLogger.d(message = "Success! getMarineWeatherForecast Inside WeatherHomeViewModel : ${result.data}")
                    }

            }
        }.launchIn(viewModelScope)
    }
}
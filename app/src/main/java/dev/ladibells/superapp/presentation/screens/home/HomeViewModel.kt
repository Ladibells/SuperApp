package dev.ladibells.superapp.presentation.screens.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.superapp.data.FestivalsData
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.constants.AppConstants
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.weather.domain.model.CurrentWeatherResponse
import dev.ladibells.weather.domain.usecase.GetCurrentWeatherUseCase
import dev.ladibells.weather.domain.usecase.GetUserLocationUseCase
import dev.ladibells.weather.presentation.screens.weather_home.WeatherHomeUIState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient,
//    private val getFestivalsData: FestivalsDataUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeUiState())
    val state: State<HomeUiState> = _state


    init {
        AppLogger.d(message = "Inside Home ViewModel")
//        supabaseClient.let {
//            AppLogger.d(message = "Inside Home ViewModel we have supabase client")
//            AppLogger.d(message = "Supabase client is $it")
////            AppLogger.d(message = "Supabase client is ${supabaseClient.auth}")
//            AppLogger.d(message = "Supabase client is ${supabaseClient.postgrest}")
//            AppLogger.d(message = "Supabase client is $supabaseClient")
//            getFestivalsData()
//
//        }
        getUserLastLocationFromDB()
    }

    private fun getFestivalsData() {
        viewModelScope.launch {
            val result = supabaseClient.postgrest["FestivalsData"].select()
            val festivals = result.decodeList<FestivalsData>()
            AppLogger.d(message = "Festivals are ${festivals.size}")
            AppLogger.d(message = "Festivals are $festivals")

            festivals.getOrNull(0)?.also { festival ->
                festival.festivalDate?.also {
                    festival.festivalDate?.also {
                        if (isTodayFestival(it)) {
                            _state.value = HomeUiState(
                                isLoading = false,
                                festivalName = festival.festivalName,
                                festivalDate = festival.festivalDate,
                                festivalDescription = festival.festivalDescription
                            )
                        } else {
                            _state.value = HomeUiState(
                                isLoading = false,

                            )
                        }
                    }
                }
            }
        }
    }

    fun isTodayFestival(festivalDate: String?): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val parsedDate = LocalDate.parse(festivalDate, formatter)
            val today = LocalDate.now()
            parsedDate.isEqual(today)
        } catch (e: DateTimeParseException) {
            return false
        }
    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Fetched UserLocationFromDB = $cityName")
            _state.value = _state.value.copy(selectedCity = cityName)
            fetchCurrentLocationWeather(location = cityName)
        }
    }

    fun refresh() {
        getUserLastLocationFromDB()
    }

    fun fetchCurrentLocationWeather(location: String) {
        AppLogger.d(message = "Inside fetchCurrentLocationWeather of WeatherHomeViewModel")
        viewModelScope.launch {
            getCurrentWeather(accessKey = AppConstants.WEATHER_ACCESS_KEY, query = location)
        }
    }

    private suspend fun getCurrentWeather(accessKey: String, query: String) {
        AppLogger.d(message = "Inside getCurrentWeather of HomeViewModel")
        getCurrentWeatherUseCase(accessKey, query).onEach { result ->
            AppLogger.d(message = "Inside onEach of getCurrentWeather of HomeViewModel")
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = null,
                        weatherHomeUIState = WeatherHomeUIState(
                            locationName = result.data?.locationName,
                            temperature = "${ceil(result.data?.temperatureC ?: 0.0).toInt()}°C",
                            weatherIcon = result.data?.weatherIcon,
                            observationTime = result.data?.observationTime,
                            airQuality03 = ceil(result.data?.airQuality03 ?: 0.0).toInt().toString(),
                        ),
                    )
                    AppLogger.d( message = "Success! getCurrentWeather of HomeViewModel\n Fetched WeatherHomeUIState : ${result.data}")
                }
                is Resource.Error -> {
                    AppLogger.d( message = "Error! getCurrentWeather of HomeViewModel: ${result.message}")
                    _state.value = HomeUiState(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                    AppLogger.d( message = "Loading! Inside getCurrentWeather of HomeViewModel")
                }
            }
        }.launchIn(viewModelScope)
    }
}

























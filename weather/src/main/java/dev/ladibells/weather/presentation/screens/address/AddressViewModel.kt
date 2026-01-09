package dev.ladibells.weather.presentation.screens.address

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.weather.domain.usecase.GetPopularCitiesUseCase
import dev.ladibells.weather.domain.usecase.GetUserLocationUseCase
import dev.ladibells.weather.domain.usecase.SaveUserLocationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val getPopularCitiesUseCase: GetPopularCitiesUseCase,
    private val saveUserLocationUseCase: SaveUserLocationUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase
): ViewModel() {

    private val _state = mutableStateOf(AddressScreenState())
    val state: State<AddressScreenState> = _state

    init {
        AppLogger.d(message = "Inside AddressViewModel")
        getPopularCities()
        getUserLastLocationFromDB()
    }

    private fun getPopularCities() {
        AppLogger.d(message = "Inside getPopularCities")
        viewModelScope.launch {
            AppLogger.d(message = "Inside viewModelScope")
            getPopularCitiesUseCase().onEach { result ->
                AppLogger.d(message = "Inside onEach")
                when (result) {
                    is Resource.Loading -> {
                        AppLogger.d(message = "Inside Loading state")
                        _state.value = AddressScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                        AppLogger.d(message = " Success !!!!!!!!!!!!!!  size = ${result.data?.size}")
                        _state.value = AddressScreenState(
                            isLoading = false,
                            getPopularCities = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        AppLogger.d(message = "Error !!!!!!!!!!")
                        _state.value = AddressScreenState(
                            isLoading = false,
                            errorMessages = result.message ?: "Something went wrong"
                        )
                        AppLogger.d(message = "Error message = ${result.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: LocationUIEvent) {
        when (event) {
            is LocationUIEvent.CityItemClicked -> {
                AppLogger.d(message = "Inside event LocationUIEvent - CityItemClicked")
                AppLogger.d(message = "CityName = ${event.cityName}")
                saveUserLocationUseCase(cityName = event.cityName)
                getUserLastLocationFromDB()
            }
        }
    }

    private fun updatedCitiesState(cityName: String) {
        val updatedCities = _state.value.getPopularCities.map {
            if (it.name == cityName) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _state.value = _state.value.copy(
            isLoading = false,
            getPopularCities = updatedCities,
            selectedCity = cityName
        )

    }

    private fun getUserLastLocationFromDB() {
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Inside getUserLocationFromDB")
            AppLogger.d(message = "Fetched UserLocationFromDB:- CityName = $cityName")
            _state.value = _state.value.copy(selectedCity = cityName)
            updatedCitiesState(cityName = cityName)

        }

    }
}
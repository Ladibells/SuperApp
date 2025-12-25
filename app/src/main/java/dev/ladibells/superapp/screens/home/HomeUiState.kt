package dev.ladibells.superapp.screens.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val festivalName: String ?= null,
    val festivalDate: String ?= null,
    val festivalDescription: String ?= null,
    val selectedCity: String = "",
//    val weatherHomeUIState: WeatherHomeUIState? = null
)

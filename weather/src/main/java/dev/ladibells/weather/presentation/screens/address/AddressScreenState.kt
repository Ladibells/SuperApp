package dev.ladibells.weather.presentation.screens.address

import dev.ladibells.datasource.local.entity.CityItem

data class AddressScreenState(
    val isLoading: Boolean = false,
    val getPopularCities: List<CityItem> = emptyList(),
    val errorMessages: String = "",
    val selectedCity : String = ""
)

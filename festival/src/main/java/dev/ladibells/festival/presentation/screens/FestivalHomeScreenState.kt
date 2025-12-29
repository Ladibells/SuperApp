package dev.ladibells.festival.presentation.screens

data class FestivalHomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val festivalName: String ?= null,
    val festivalDate: String ?= null,
    val festivalDescription: String ?= null,
)

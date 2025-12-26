package dev.ladibells.wealth.presentation.screens.wealthhome

import dev.ladibells.datasource.local.entity.Coin

data class WealthHomeScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)

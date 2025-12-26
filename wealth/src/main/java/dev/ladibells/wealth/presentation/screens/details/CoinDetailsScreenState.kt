package dev.ladibells.wealth.presentation.screens.details

import dev.ladibells.wealth.domain.model.CoinDetail
import dev.ladibells.wealth.domain.model.CoinTickerInformation

data class CoinDetailsScreenState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String? = null,
    val coinTickerInformation: CoinTickerInformation? = null,
    val errorFetchingCoinTickerInformation: String? = null,
    val isLoadingCoinTickerInformation: Boolean = false
)
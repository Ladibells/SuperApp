package dev.ladibells.wealth.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.wealth.domain.usecase.GetCoinDetailsUseCase
import dev.ladibells.wealth.domain.usecase.GetCoinTickerInformationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val getCoinTickerInformationUseCase: GetCoinTickerInformationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsScreenState())
    val state : MutableState<CoinDetailsScreenState> = _state

    fun fetchDetailsForCoin(coinId: String) {
        viewModelScope.launch {
            getCoinDetails(coinId)
        }
    }

    private suspend fun getCoinDetails(coinId: String) {
        AppLogger.d(message = "Api call function for getCoinDetails")
        getCoinDetailsUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    AppLogger.d(message = "Success stage for getCoinDetails")
                    _state.value = CoinDetailsScreenState(coinDetail = result.data)
                    getCoinTickerInformation(coinId)
                }
                is Resource.Error -> {
                    AppLogger.e(message = "Error stage for getCoinDetails ${result.message}")
                    _state.value = CoinDetailsScreenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    AppLogger.d(message = "Loading stage for getCoinDetails")
                    _state.value = CoinDetailsScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getCoinTickerInformation(coinId: String) {
        AppLogger.d(message = "Api call function for getCoinTickerInformation")
        getCoinTickerInformationUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(coinTickerInformation = result.data)
                    _state.value = _state.value.copy(isLoadingCoinTickerInformation = false)
                    AppLogger.d(message = "Success stage for getCoinTickerInformation")
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        errorFetchingCoinTickerInformation = result.message ?: "An unexpected error occurred"
                    )
                    AppLogger.e(message = "Error stage for getCoinTickerInformation ${result.message}")
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoadingCoinTickerInformation = true)
                    AppLogger.d(message = "Loading stage for getCoinTickerInformation")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun coinPriceNumberFormat(): NumberFormat {
        NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
            return this
        }
    }
}
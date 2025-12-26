package dev.ladibells.wealth.presentation.screens.wealthhome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.wealth.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WealthViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
): ViewModel() {

    private val _state = mutableStateOf(WealthHomeScreenState())
    val state: State<WealthHomeScreenState> = _state

    init {
        viewModelScope.launch {
            getCoins()
        }
    }

    suspend fun getCoins() {
        AppLogger.d(message = "getCoins")
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = WealthHomeScreenState(coins = result.data ?: emptyList())
                    AppLogger.d(message = "getCoins Success")
                }
                is Resource.Error -> {
                    _state.value = WealthHomeScreenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    AppLogger.d(message = "getCoins Error")
                }
                is Resource.Loading -> {
                    _state.value = WealthHomeScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
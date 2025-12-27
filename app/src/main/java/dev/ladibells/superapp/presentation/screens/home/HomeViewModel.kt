package dev.ladibells.superapp.presentation.screens.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ladibells.superapp.data.FestivalsData
import dev.ladibells.utilities.logging.AppLogger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _state = mutableStateOf(HomeUiState())
    val state: State<HomeUiState> = _state


    init {
        supabaseClient.let {
            AppLogger.d(message = "Inside Home ViewModel we have supabase client")
            AppLogger.d(message = "Supabase client is $it")
//            AppLogger.d(message = "Supabase client is ${supabaseClient.auth}")
            AppLogger.d(message = "Supabase client is ${supabaseClient.postgrest}")
            AppLogger.d(message = "Supabase client is $supabaseClient")
            getFestivalsData()
        }
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
}
package dev.ladibells.wealth.domain.usecase

import com.google.gson.annotations.SerializedName
import dev.ladibells.utilities.Resource
import dev.ladibells.wealth.domain.model.CoinDetail
import dev.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> {
        return repository.getCoinDetails(coinId)
    }
}


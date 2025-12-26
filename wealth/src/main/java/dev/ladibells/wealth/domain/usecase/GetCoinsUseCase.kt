package dev.ladibells.wealth.domain.usecase

import com.google.gson.annotations.SerializedName
import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.utilities.Resource
import dev.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() : Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }

}

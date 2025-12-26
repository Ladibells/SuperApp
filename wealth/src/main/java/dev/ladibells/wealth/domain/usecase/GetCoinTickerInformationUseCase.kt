package dev.ladibells.wealth.domain.usecase

import dev.ladibells.utilities.Resource
import dev.ladibells.wealth.domain.model.CoinTickerInformation
import dev.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinTickerInformationUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String) : Flow<Resource<CoinTickerInformation>> {
        return repository.getCoinTickerInformation(coinId)
    }

}

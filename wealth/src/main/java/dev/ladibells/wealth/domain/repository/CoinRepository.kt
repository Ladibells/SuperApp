package dev.ladibells.wealth.domain.repository

import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.utilities.Resource
import dev.ladibells.wealth.domain.model.CoinDetail
import dev.ladibells.wealth.domain.model.CoinTickerInformation
import kotlinx.coroutines.flow.Flow


interface CoinRepository {
    suspend fun getCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinDetails(coinId: String) : Flow<Resource<CoinDetail>>
    suspend fun getCoinTickerInformation(coinId: String) : Flow<Resource<CoinTickerInformation>>

}
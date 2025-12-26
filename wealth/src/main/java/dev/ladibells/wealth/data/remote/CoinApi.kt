package dev.ladibells.wealth.data.remote

import dev.ladibells.wealth.data.remote.dto.CoinDetailDto
import dev.ladibells.wealth.data.remote.dto.CoinDto
import dev.ladibells.wealth.data.remote.dto.CoinTickerInformationDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("/v1/coins/{coin_Id}")
    suspend fun getCoinDetails(@Path("coin_Id") coinId: String) : CoinDetailDto

    @GET("/v1/tickers/{coin_Id}")
    suspend fun getCoinTickerInformation(@Path("coin_Id") coinId: String) : CoinTickerInformationDTO

}
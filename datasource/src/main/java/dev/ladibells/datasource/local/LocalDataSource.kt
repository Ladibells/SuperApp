package dev.ladibells.datasource.local

import dev.ladibells.datasource.local.entity.Coin

interface LocalDataSource {
    // wealth module
    fun getCoinListFromDB() : List<Coin>
    fun insertCoinListInDB(coinList: List<Coin>)
    fun deleteAllCoinsFromDB()

    //Wealth module
    fun insertUserLocationInDB(cityName: String)
    fun getUserLocationFromDB() : String

}
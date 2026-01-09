package dev.ladibells.datasource.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.datasource.local.entity.UserLocation

class LocalDataSourceImpl(
    @ApplicationContext val context: Context
) : LocalDataSource {

    private val appDB : AppDB = AppDB.getDatabase(context)

    //wealth Module
    override fun getCoinListFromDB(): List<Coin> {
        return appDB.getCoinsDao().getCoinListFromDB()
    }

    override fun insertCoinListInDB(coinList: List<Coin>) {
        appDB.getCoinsDao().insertCoinListInDB(coinList)
    }

    override fun deleteAllCoinsFromDB() {
        appDB.getCoinsDao().deleteAllCoinsFromDB()
    }

    //weather Module
    override fun insertUserLocationInDB(cityName: String) {
        appDB.getLocationDao().insertUserLocationInDB(userLocation = UserLocation(cityName = cityName))
    }

    override fun getUserLocationFromDB(): String {
        return appDB.getLocationDao().getUserLocationFromDB()?.cityName ?: ""
    }
}
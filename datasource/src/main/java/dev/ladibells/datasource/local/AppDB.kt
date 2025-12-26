package dev.ladibells.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ladibells.datasource.local.dao.CoinsDao
import dev.ladibells.datasource.local.dao.LocationDao
import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.datasource.local.entity.UserLocation

@Database(entities = [Coin::class, UserLocation::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun getCoinsDao() : CoinsDao

    abstract fun getLocationDao() : LocationDao

    companion object {

        @Volatile
        private var instance : AppDB? = null

        fun getDatabase(context: Context) : AppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context) : AppDB {
            return Room.databaseBuilder(
                context,
                AppDB::class.java,
                TableConstants.APP_DB_NAME
            ).fallbackToDestructiveMigration(false)
                .allowMainThreadQueries()
                .build()
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}

object TableConstants {
    const val APP_DB_NAME = "SuperApp_Database"
    const val COIN_LIST = "Coin_List"
    const val USER_LOCATION = "User_Location"

}
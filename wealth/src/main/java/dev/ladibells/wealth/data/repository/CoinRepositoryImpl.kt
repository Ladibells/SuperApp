package dev.ladibells.wealth.data.repository

import com.google.gson.annotations.SerializedName
import dev.ladibells.datasource.local.LocalDataSource
import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.utilities.Resource
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.wealth.data.remote.CoinApi
import dev.ladibells.wealth.data.remote.dto.toCoin
import dev.ladibells.wealth.data.remote.dto.toCoinDetail
import dev.ladibells.wealth.data.remote.dto.toCoinTickerInformation
import dev.ladibells.wealth.domain.model.CoinDetail
import dev.ladibells.wealth.domain.model.CoinTickerInformation
import dev.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi,
    private val localDataSource: LocalDataSource
) : CoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coinListFromDB = getCoinListFromDB()
            coinListFromDB?.also {
                emit(Resource.Success(it))
                AppLogger.d(message = "Success = coinList from DB size is ${it.size}")
//                return@flow
            }
            val coins = api.getCoins().map { it.toCoin() }
            insertCoinListInDataBase(coins)
            emit(Resource.Success(coins))
            AppLogger.d(message = "Success = coinListFromServer size is ${coins.size}")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    override suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = api.getCoinDetails(coinId = coinId).toCoinDetail()
            emit(Resource.Success(coinDetails))
            AppLogger.d(message = "Success = getCoinDetails")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            AppLogger.d(message = "Error = getCoinDetails")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            AppLogger.d(message = "Error = getCoinDetails")

        }
    }

    override suspend fun getCoinTickerInformation(coinId: String): Flow<Resource<CoinTickerInformation>> = flow {
        try {
            emit(Resource.Loading())
            val coinTickerInformation = api.getCoinTickerInformation(coinId = coinId).toCoinTickerInformation()
            emit(Resource.Success(coinTickerInformation))
            AppLogger.d(message = "Success = getCoinTickerInformation")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    private suspend fun insertCoinListInDataBase(coinList: List<Coin>) {
        withContext(Dispatchers.IO) {
            localDataSource.insertCoinListInDB(coinList)
            AppLogger.d(message = "Success = insertCoinListInDataBase with size ${coinList.size}")
        }
    }

    private suspend fun getCoinListFromDB(): List<Coin>? =
        withContext(Dispatchers.IO) {
            val coinList = localDataSource.getCoinListFromDB()
            if (coinList.isEmpty()) null else coinList
        }

}

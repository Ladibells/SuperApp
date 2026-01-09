package dev.ladibells.weather.domain.usecase

import dev.ladibells.datasource.local.LocalDataSource
import dev.ladibells.utilities.logging.AppLogger
import javax.inject.Inject

class SaveUserLocationUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    operator fun invoke(cityName: String) {
        localDataSource.insertUserLocationInDB(cityName)
        AppLogger.d(message = "Inside SaveUserLocationUseCase invoke function")
    }
}
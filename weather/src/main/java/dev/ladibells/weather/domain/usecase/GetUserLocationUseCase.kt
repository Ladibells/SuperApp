package dev.ladibells.weather.domain.usecase

import dev.ladibells.datasource.local.LocalDataSource
import dev.ladibells.utilities.logging.AppLogger
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    operator fun invoke(): String {
        AppLogger.d(message = "Inside GetUserLocationUseCase invoke function, fetched UserLocation = ${localDataSource.getUserLocationFromDB()}")
        return localDataSource.getUserLocationFromDB()
    }
}
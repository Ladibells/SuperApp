package dev.ladibells.weather.domain.repository

import dev.ladibells.datasource.local.entity.CityItem
import dev.ladibells.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface AddressRepository {

    suspend fun getPopularCities() : Flow<Resource<List<CityItem>>>
}
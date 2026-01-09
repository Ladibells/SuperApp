package dev.ladibells.weather.domain.usecase

import dev.ladibells.datasource.local.entity.CityItem
import dev.ladibells.utilities.Resource
import dev.ladibells.weather.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularCitiesUseCase @Inject constructor(
    private val addressRepository: AddressRepository
) {
    suspend operator fun invoke() : Flow<Resource<List<CityItem>>> {
        return addressRepository.getPopularCities()
    }
}
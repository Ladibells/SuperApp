package dev.ladibells.weather.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ladibells.utilities.constants.AppConstants
import dev.ladibells.weather.data.remote.WeatherApi
import dev.ladibells.weather.data.repository.AddressRepositoryImpl
import dev.ladibells.weather.data.repository.WeatherRepositoryImpl
import dev.ladibells.weather.domain.repository.AddressRepository
import dev.ladibells.weather.domain.repository.WeatherRepository
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @Singleton
    fun provideAddressRepository(@ApplicationContext context: Context) : AddressRepository {
        return AddressRepositoryImpl(context = context)
    }


    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Kotlinx Serialization JSON config
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_WEATHER) // must end with /
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(client)
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    @Singleton
    fun providesWeatherRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}


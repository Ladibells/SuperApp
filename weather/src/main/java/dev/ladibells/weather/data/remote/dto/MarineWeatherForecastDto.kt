package dev.ladibells.weather.data.remote.dto

import dev.ladibells.weather.domain.model.MarineWeatherForecast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarineWeatherForecastDto(
    @SerialName("location") val location: LocationInfo,
    @SerialName("forecast") val forecast: Forecast
)

@Serializable
data class LocationInfo(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerialName("tz_id") val tzId: String,
    @SerialName("localtime_epoch") val localtimeEpoch: Long,
    val localtime: String
)

@Serializable
data class Forecast(
    @SerialName("forecastday") val forecastday: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    val date: String,
    @SerialName("date_epoch") val dateEpoch: Long,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>? = null
)

@Serializable
data class Day(
    @SerialName("maxtemp_c") val maxtempC: Double,
    @SerialName("maxtemp_f") val maxtempF: Double,
    @SerialName("mintemp_c") val mintempC: Double,
    @SerialName("mintemp_f") val mintempF: Double,
    @SerialName("avgtemp_c") val avgtempC: Double,
    @SerialName("avgtemp_f") val avgtempF: Double,
    @SerialName("maxwind_mph") val maxwindMph: Double,
    @SerialName("maxwind_kph") val maxwindKph: Double,
    @SerialName("totalprecip_mm") val totalprecipMm: Double,
    @SerialName("totalprecip_in") val totalprecipIn: Double,
    @SerialName("totalsnow_cm") val totalsnowCm: Double,
    @SerialName("avgvis_km") val avgvisKm: Double,
    @SerialName("avgvis_miles") val avgvisMiles: Double,
    @SerialName("avghumidity") val avghumidity: Double,
    @SerialName("daily_will_it_rain") val dailyWillItRain: Int? = null,
    @SerialName("daily_chance_of_rain") val dailyChanceOfRain: Int? = null,
    @SerialName("daily_will_it_snow") val dailyWillItSnow: Int? = null,
    @SerialName("daily_chance_of_snow") val dailyChanceOfSnow: Int? = null,
    val condition: Condition,
    val uv: Double,
    val tides: List<TideData>? = null
)

@Serializable
data class TideData(
    @SerialName("tide") val tide: List<Tide>,
)

@Serializable
data class Tide(
    @SerialName("tide_time") val tideTime: String,
    @SerialName("tide_height_mt") val tideHeightMt: String,
    @SerialName("tide_type") val tideType: String
)

@Serializable
data class ConditionInfo(
    val text: String,
    val icon: String,
    val code: Int
)

@Serializable
data class Hour(
    @SerialName("time_epoch") val timeEpoch: Long,
    val time: String,
    @SerialName("temp_c") val tempC: Double,
    @SerialName("temp_f") val tempF: Double,
    @SerialName("is_day") val isDay: Int,
    val condition: ConditionInfo,
    @SerialName("wind_mph") val windMph: Double,
    @SerialName("wind_kph") val windKph: Double,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDir: String,
    @SerialName("pressure_mb") val pressureMb: Double,
    @SerialName("pressure_in") val pressureIn: Double,
    @SerialName("precip_mm") val precipMm: Double,
    @SerialName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerialName("feelslike_c") val feelslikeC: Double,
    @SerialName("feelslike_f") val feelslikeF: Double,
    @SerialName("windchill_c") val windchillC: Double,
    @SerialName("windchill_f") val windchillF: Double,
    @SerialName("heatindex_c") val heatindexC: Double,
    @SerialName("heatindex_f") val heatindexF: Double,
    @SerialName("dewpoint_c") val dewpointC: Double,
    @SerialName("dewpoint_f") val dewpointF: Double,
    @SerialName("will_it_rain") val willItRain: Int,
    @SerialName("chance_of_rain") val chanceOfRain: Int,
    @SerialName("will_it_snow") val willItSnow: Int,
    @SerialName("chance_of_snow") val chanceOfSnow: Int,
    @SerialName("vis_km") val visKm: Double,
    @SerialName("vis_miles") val visMiles: Double,
    @SerialName("gust_mph") val gustMph: Double,
    @SerialName("gust_kph") val gustKph: Double,
    val uv: Double,
    @SerialName("sig_ht_mt") val sigHtMt: Double,
    @SerialName("swell_ht_mt") val swellHtMt: Double,
    @SerialName("swell_ht_ft") val swellHtFt: Double,
    @SerialName("swell_dir") val swellDir: String,
    @SerialName("swell_period_secs") val swellPeriodSecs: Double,
    @SerialName("swell_dir_16_point") val swellDir16Point: String,
    @SerialName("water_temp_c") val waterTempC: Double,
    @SerialName("water_temp_f") val waterTempF: Double
)

fun MarineWeatherForecastDto.toMarineWeatherForecast(): MarineWeatherForecast {
    return MarineWeatherForecast(
        locationName = this.location.name,
        forecastDayDate = this.forecast.forecastday[0].date,
        maxTempC = this.forecast.forecastday[0].day.maxtempC,
        minTempC = this.forecast.forecastday[0].day.mintempC,
        weatherIcon = "https:${this.forecast.forecastday[0].day.condition.icon}",
        avgHumidity = this.forecast.forecastday[0].day.avghumidity,
        summaryOfTheDay = this.forecast.forecastday[0].day.condition.text,
        summaryIconOfTheDay = "https:${forecast.forecastday[0].day.condition.icon}",
        sunrise = this.forecast.forecastday[0].astro.sunrise,
        sunset = this.forecast.forecastday[0].astro.sunset,
        moonrise = this.forecast.forecastday[0].astro.moonrise,
        moonset = this.forecast.forecastday[0].astro.moonset,
    )
}
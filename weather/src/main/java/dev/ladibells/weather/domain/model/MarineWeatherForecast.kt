package dev.ladibells.weather.domain.model

data class MarineWeatherForecast(
    val locationName: String? = null,
    val forecastDayDate: String? = null,
    val maxTempC: Double? = null,
    val minTempC: Double? = null,
    val avgHumidity: Double? = null,
    val summaryOfTheDay: String? = null,
    val summaryIconOfTheDay: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val moonrise: String? = null,
    val moonset: String? = null,
    val weatherIcon: String? = null,
)

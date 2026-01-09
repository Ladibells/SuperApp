package dev.ladibells.weather.presentation.screens.weather_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import dev.ladibells.design.R
import dev.ladibells.design.components.AppToolBar
import dev.ladibells.design.components.TextComponent
import dev.ladibells.design.ui.theme.lightPrimaryColor
import dev.ladibells.weather.presentation.components.WeatherFieldDetailsComponent

@Composable
fun WeatherHomeScreen(
    weatherHomeViewModel: WeatherHomeViewModel = hiltViewModel(),
    primaryButtonClicked: () -> Unit = {}
) {
    val state = weatherHomeViewModel.state.value

    Scaffold(
        topBar = {
            AppToolBar(
                title = state.locationName ?: stringResource(id = R.string.weather_details),
                isBackButtonVisible = true,
                primaryButtonClicked = { primaryButtonClicked.invoke() }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // -----------------------------
            // HERO SECTION
            // -----------------------------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                state.temperature?.let {
                    TextComponent(
                        textValue = it,
                        fontSizeValue = 48.sp,
                        fontWeightValue = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                state.summaryIconOfTheDay?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                state.summaryOfTheDay?.let {
                    TextComponent(
                        textValue = it,
                        fontSizeValue = 22.sp,
                        fontWeightValue = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // -----------------------------
            // QUICK DETAILS GRID
            // -----------------------------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                WeatherMiniCard(
                    title = "Humidity",
                    value = "${state.avgHumidity ?: "--"}%",
                    icon = R.drawable.humidity
                )

                WeatherMiniCard(
                    title = "Air Quality",
                    value = state.airQuality03 ?: "--",
                    icon = R.drawable.air_quality
                )

                WeatherMiniCard(
                    title = "Feels Like",
                    value = state.temperature ?: "--",
                    icon = R.drawable.temperature
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // -----------------------------
            // FORECAST SUMMARY CARD
            // -----------------------------
            WeatherSectionCard(title = "Today's Forecast") {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextComponent(
                            textValue = "Max Temp",
                            fontSizeValue = 18.sp
                        )
                        TextComponent(
                            textValue = "${state.maxTempC ?: "--"}°C",
                            fontSizeValue = 18.sp,
                            fontWeightValue = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextComponent(
                            textValue = "Min Temp",
                            fontSizeValue = 18.sp
                        )
                        TextComponent(
                            textValue = "${state.minTempC ?: "--"}°C",
                            fontSizeValue = 18.sp,
                            fontWeightValue = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // -----------------------------
            // SUNRISE / SUNSET CARD
            // -----------------------------
            WeatherSectionCard(title = "Sun & Moon") {
                Column(modifier = Modifier.padding(16.dp)) {

                    WeatherSunMoonRow(
                        label = "Sunrise",
                        value = state.sunrise ?: "--",
                        icon = R.drawable.sunrise
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    WeatherSunMoonRow(
                        label = "Sunset",
                        value = state.sunset ?: "--",
                        icon = R.drawable.sunset
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    WeatherSunMoonRow(
                        label = "Moonrise",
                        value = state.moonrise ?: "--",
                        icon = R.drawable.moonrise
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    WeatherSunMoonRow(
                        label = "Moonset",
                        value = state.moonset ?: "--",
                        icon = R.drawable.moonset
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun WeatherMiniCard(title: String, value: String, icon: Int) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(lightPrimaryColor)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextComponent(textValue = title, fontSizeValue = 14.sp)
        TextComponent(textValue = value, fontSizeValue = 18.sp, fontWeightValue = FontWeight.Bold)
    }
}

@Composable
fun WeatherSectionCard(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(lightPrimaryColor)
    ) {
        TextComponent(
            textValue = title,
            fontSizeValue = 20.sp,
            fontWeightValue = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp)
        )
        content()
    }
}

@Composable
fun WeatherSunMoonRow(label: String, value: String, icon: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextComponent(textValue = label, fontSizeValue = 18.sp)
        }
        TextComponent(textValue = value, fontSizeValue = 18.sp, fontWeightValue = FontWeight.Medium)
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherHomeScreenPreview() {
    WeatherHomeScreen()
}


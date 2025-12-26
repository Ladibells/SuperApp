package dev.ladibells.superapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import dev.ladibells.design.R
import dev.ladibells.design.components.AppToolBar
import dev.ladibells.design.components.BannerComponent
import dev.ladibells.design.components.WeatherBannerComponent
import dev.ladibells.design.ui.theme.whiteColor

//@Composable
//fun HomeScreen(
//) {
//    Scaffold(
//        topBar = { AppToolBar() }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .wrapContentSize()
//                .background(whiteColor),
//        ) {
//        }
//    }
//}


@Composable
fun HomeScreen(
    primaryButtonClicked: () -> Unit = {},
    wealthBannerClicked: () -> Unit = {},
//    viewModel: HomeViewModel = hiltViewModel(),
    festivalBannerClicked: () -> Unit = {},
    addAddressClicked: () -> Unit = {}
) {

//    val state = viewModel.state.value

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            viewModel.refresh()
        }
    }
    Scaffold (
        topBar = {
            AppToolBar(
//                title = if (state.selectedCity.isNotEmpty()) state.selectedCity else stringResource(R.string.add_address),
                title = stringResource(R.string.add_address),
                isNotificationButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                },
                primaryTextClicked = {
                    addAddressClicked()
                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
                .background(color = whiteColor)
                .padding(innerPadding)
        ){

//            if (state.isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//            }

//            if (state.festivalName != null) {
//                FestivalBannerComponent(
//                    title = state.festivalName,
//                    description = state.festivalDescription,
//                    imageUrl = null,
//                    resourceValue = drawable.holi,
//                    festivalBannerClicked = { festivalBannerClicked() }
//                )
//            }

            BannerComponent(
                title = stringResource(R.string.wealth),
                description = stringResource(R.string.investment_ideas_for_you),
                imageUrl = null,
                resourceValue = R.drawable.ic_wealth1,
                bannerClicked = {
                    wealthBannerClicked()
                }
            )

//            WeatherBannerComponent(
//                title = stringResource(R.string.weather),
//                description = if (state.weatherHomeUIState?.temperature != null) "${state.weatherHomeUIState.temperature} ${state.weatherHomeUIState.unit}" else null,
//                imageUrl = null,
//                resourceValue = null
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
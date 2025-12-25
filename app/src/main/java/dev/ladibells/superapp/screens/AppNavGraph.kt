package dev.ladibells.superapp.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.ladibells.superapp.screens.home.HomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
        ) {
            composable(
                route = Screen.HomeScreen.route,
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                }
            ) {
                HomeScreen(
//                    primaryButtonClicked = {
//                    },
//                    wealthBannerClicked = {
//                        navController.navigate(Screen.WealthHomeScreen.route)
//                    },
//                    festivalBannerClicked = {
//                        navController.navigate(Screen.FestivalHomeScreen.route)
//                    },
//                    addAddressClicked = {
//                        navController.navigate(Screen.AddressScreen.route)
//                    }
                )
            }
        }
    }
}
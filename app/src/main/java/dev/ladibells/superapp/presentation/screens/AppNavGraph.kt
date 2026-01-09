package dev.ladibells.superapp.presentation.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.ladibells.superapp.presentation.screens.home.HomeScreen
import dev.ladibells.utilities.logging.AppLogger
import dev.ladibells.wealth.presentation.screens.details.CoinDetailsScreen
import dev.ladibells.wealth.presentation.screens.wealthhome.WealthHomeScreen
import dev.ladibells.weather.presentation.screens.address.AddressScreen
import dev.ladibells.weather.presentation.screens.weather_home.WeatherHomeScreen

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
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
//                arguments = listOf(
//                    navArgument(name = "${ScreenArguments.COIN_ID}") {
//                        type = NavType.StringType
//                    }
//                )
            ) {
                HomeScreen(
                    primaryButtonClicked = {
//                        navController.navigate(Screen.WealthHomeScreen.route)
                    },
                    wealthBannerClicked = {
                        navController.navigate(Screen.WealthHomeScreen.route)
                    },
                    addAddressClicked = {
                        navController.navigate(Screen.AddressScreen.route)
                    },
                    weatherBannerClicked = {
                        navController.navigate(Screen.WeatherHomeScreen.route)
                    }
                )
            }

            composable(
                route = Screen.WealthHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                WealthHomeScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    },
                    coinItemClicked = { coinId ->
                        navController.navigate(Screen.CoinDetailsScreen.route + "/$coinId")
                    }
                )
            }

            composable(
                route = "${Screen.CoinDetailsScreen.route}/{${ScreenArguments.COIN_ID}}",
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                arguments = listOf(
                    navArgument(name = "${ScreenArguments.COIN_ID}") {
                        type = NavType.StringType
                    }
                )
            ) {
                it.arguments?.getString("${ScreenArguments.COIN_ID}")?.also { coinId ->
                    AppLogger.d(message = "CoinId is === $coinId")
                    CoinDetailsScreen(
                        coinId,
                        backButtonClicked = {
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable(
                route = Screen.AddressScreen.route
            ) {
                AddressScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.WeatherHomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )
                },
                popEnterTransition = null
            ) {
                WeatherHomeScreen(
                    primaryButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
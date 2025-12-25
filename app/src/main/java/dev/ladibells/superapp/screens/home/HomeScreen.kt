package dev.ladibells.superapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.ladibells.superapp.components.AppToolBar
import dev.ladibells.superapp.ui.theme.whiteColor

@Composable
fun HomeScreen(
) {
    Scaffold(
        topBar = { AppToolBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .background(whiteColor),
        ) {
        }
    }
}
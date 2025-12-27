package dev.ladibells.festival.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.ladibells.design.R
import dev.ladibells.design.components.AppToolBar

@Composable
fun FestivalHomeScreen() {

    Scaffold(
        topBar = {
            AppToolBar(
                title = stringResource(id = R.string.wealth),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) { }
    }
}
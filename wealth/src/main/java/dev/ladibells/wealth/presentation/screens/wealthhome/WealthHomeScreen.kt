package dev.ladibells.wealth.presentation.screens.wealthhome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ladibells.design.R
import dev.ladibells.design.components.AppToolBar
import dev.ladibells.design.ui.theme.whiteColor
import dev.ladibells.wealth.domain.repository.components.CoinListItem

@Composable
fun WealthHomeScreen(
    primaryButtonClicked: () -> Unit = {},
    viewModel: WealthViewModel = hiltViewModel(),
    coinItemClicked: (String) -> Unit = {}
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            AppToolBar(
                title = stringResource(R.string.wealth),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteColor)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                    .background(whiteColor)
            ) {

                if (!state.error.isNullOrEmpty()) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinListItem(
                            coin = coin,
                            coinItemClicked = { coinId ->
                                coinItemClicked(coinId)
                            }
                        )
                    }
                }
            }



            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WealthHomeScreenPreview() {
    WealthHomeScreen()
}
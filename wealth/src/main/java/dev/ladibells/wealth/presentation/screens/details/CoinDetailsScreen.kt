package dev.ladibells.wealth.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ladibells.design.R
import dev.ladibells.design.components.AppToolBar
import dev.ladibells.design.components.ChipTextComponent
import dev.ladibells.design.components.ImageComponent
import dev.ladibells.design.components.TextComponent
import dev.ladibells.design.ui.theme.greenColor
import dev.ladibells.wealth.domain.model.CoinDetail
import java.text.NumberFormat
import kotlin.text.get

@Composable
fun CoinDetailsScreen(
    coinId: String,
    backButtonClicked: () -> Unit,
    coinDetailsViewModel: CoinDetailsViewModel = hiltViewModel()
) {

    val coinPriceNumberFormat = coinDetailsViewModel.coinPriceNumberFormat()

    LaunchedEffect(key1 = coinId) {
        coinDetailsViewModel.fetchDetailsForCoin(coinId)
    }
    val state = coinDetailsViewModel.state.value

    Scaffold(
        topBar = {
            AppToolBar(
                title = state.coinDetail?.name?:stringResource(R.string.wealth),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    backButtonClicked()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .wrapContentSize(Alignment.TopStart)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .wrapContentHeight(Alignment.Top)
//                    .padding(innerPadding)
            ) {
                state.coinDetail?.also { coinDetail ->
                    CoinDetailsSuccessComponent(coinDetail = coinDetail)
//                    CoinOrganisationDetailsComponent(coinDetail = coinDetail)
                }

                state.coinTickerInformation?.also { coinTickerInformation ->
                    CoinPriceInformationComponent(state = state, coinPriceNumberFormat = coinPriceNumberFormat)
                }
            }
            if (!state.error.isNullOrEmpty()) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}


@Composable
fun CoinDetailsSuccessComponent(coinDetail: CoinDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {

        CoinPrimaryInfoComponent(coinDetail)
        CoinOrganisationDetailsComponent(coinDetail)

//        coinDetail.description.let { description ->
        TextComponent(
            modifier = Modifier.wrapContentSize().padding(12.dp),
            textValue = coinDetail.description,
            fontSizeValue = 16.sp,
            fontWeightValue = FontWeight.Light
        )
//
//            Spacer(modifier = Modifier.padding(12.dp))
//
//            ImageComponent(
//                modifier = Modifier.fillMaxSize(),
//                url = coinDetail.logo
//            )
//        }
    }
}

@Composable
fun CoinPrimaryInfoComponent(coinDetail: CoinDetail) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                modifier = Modifier.size(80.dp),
                url = coinDetail.logo
            )

            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {

                TextComponent(
                    modifier = Modifier.wrapContentSize().padding(horizontal = 12.dp),
                    textValue = coinDetail.name,
                    fontSizeValue = 25.sp
                )
                TextComponent(
                    modifier = Modifier.wrapContentSize().padding(horizontal = 12.dp),
                    textValue = coinDetail.symbol,
                    fontSizeValue = 18.sp
                )

//            coinDetail.description.let { description ->
//                TextComponent(
//                    modifier = Modifier.wrapContentSize().padding(12.dp),
//                    textValue = coinDetail.description,
//                    fontSizeValue = 16.sp,
//                    textColorValue = Color.Black
//                )


//
//                ImageComponent(
//                    modifier = Modifier.fillMaxSize(),
//                    url = coinDetail.logo
//                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coinDetail.author?.also { authorName ->
                TextComponent(
                    modifier = Modifier.wrapContentSize().padding(horizontal = 12.dp),
                    textValue = authorName,
                    fontSizeValue = 18.sp,
                    textColorValue = greenColor
                )
            }
        }

    }
}

@Composable
fun CoinOrganisationDetailsComponent(coinDetail: CoinDetail) {
    Row {
        ChipTextComponent(coinDetail.orgStructure)
        ChipTextComponent(coinDetail.hashAlgorithm)
    }
}

@Composable
fun CoinPriceInformationComponent(
    state: CoinDetailsScreenState,
    coinPriceNumberFormat: NumberFormat
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
    ) {

        if (state.isLoadingCoinTickerInformation) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally).size(30.dp))

        }
        state.coinTickerInformation?.also { coinTickerInformation ->
            TextComponent(
                modifier = Modifier.wrapContentSize().padding(12.dp),
                textValue = "£ ${coinPriceNumberFormat.format(coinTickerInformation.priceInfo["USD"]?.price)}",
                fontSizeValue = 25.sp
            )
        }


    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoinDetailsPreview() {
    CoinDetailsScreen("",{})
}
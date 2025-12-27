package dev.ladibells.wealth.domain.repository.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.ladibells.datasource.local.entity.Coin
import dev.ladibells.design.components.TextComponent
import dev.ladibells.design.ui.theme.greenColor
import dev.ladibells.design.ui.theme.lightBlackColor
import dev.ladibells.design.ui.theme.primaryColor
import dev.ladibells.design.ui.theme.whiteColor

@Composable
fun CoinListItem(
    coin: Coin,
    coinItemClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(106.dp)
            .clickable {
                coinItemClicked(coin.id)
            }
            .padding(top = 18.dp, start = 18.dp, end = 18.dp)
            .border(1.dp, primaryColor, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteColor)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                TextComponent(
                    modifier = Modifier.wrapContentSize(),
                    textValue = coin.name,
                    textColorValue = primaryColor,
                    fontWeightValue = FontWeight.ExtraBold
                )
                TextComponent(
                    modifier = Modifier.wrapContentSize(),
                    textValue = coin.symbol,
                )
            }
            TextComponent(
                modifier = Modifier.wrapContentSize(),
                fontWeightValue = FontWeight.Medium,
                textValue = if (coin.isActive) "active" else "inactive",
                textColorValue = if (coin.isActive) greenColor else lightBlackColor
            )
        }
    }
}
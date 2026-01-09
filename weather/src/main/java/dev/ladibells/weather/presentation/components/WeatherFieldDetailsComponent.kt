package dev.ladibells.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ladibells.design.components.TextComponent
import dev.ladibells.design.ui.theme.lightPrimaryColor
import dev.ladibells.design.ui.theme.whiteColor

@Composable
fun WeatherFieldDetailsComponent(
    title: String,
    value: String,
    icon: Int? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(lightPrimaryColor)
    ) {
//        Spacer(modifier = Modifier.size(8.dp))

        Spacer(modifier = Modifier.size(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .wrapContentHeight()
                .padding(horizontal = 20.dp)
        ) {
            icon?.also {
                Image(
                    painter = painterResource(icon,),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
//                    .padding(all = 12.dp)
                )
            }

            TextComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                fontSizeValue = 34.sp,
                textColorValue = whiteColor,
                fontWeightValue = FontWeight.Normal,
                textValue = title
            )
        }

        Spacer(modifier = Modifier.size(18.dp))

        TextComponent(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            fontSizeValue = 34.sp,
            textColorValue = whiteColor,
            fontWeightValue = FontWeight.Medium,
            textValue = value
        )

    }
}
package dev.ladibells.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ladibells.design.ui.theme.blackColor

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    textValue: String? = null,
    textColorValue: Color = blackColor,
    fontSizeValue: TextUnit = 16.sp,
    paddingValue: Dp = 0.dp,
    fontWeightValue: FontWeight = FontWeight.Normal

) {
    textValue?.let {
        Text(
            modifier = modifier
                .padding(all = paddingValue),
            text = it,
            style = TextStyle(
                color = textColorValue,
                fontSize = fontSizeValue,
                fontWeight = fontWeightValue
            ),
            textAlign = TextAlign.Start
        )
    }
}


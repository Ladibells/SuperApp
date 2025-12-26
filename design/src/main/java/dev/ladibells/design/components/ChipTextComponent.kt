package dev.ladibells.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChipTextComponent(textValue: String) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
    ) {
        TextComponent(
            modifier = Modifier.wrapContentSize().padding(all = 12.dp),
            textValue = textValue,
            fontSizeValue = 16.sp
        )
    }
}
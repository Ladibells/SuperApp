package dev.ladibells.superapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ladibells.superapp.R
import dev.ladibells.superapp.ui.theme.*

@Composable
fun AppToolBar(
    title: String? = null,
    isBackButtonVisible: Boolean = false,
    isNotificationButtonVisible: Boolean = true,
    primaryButtonClicked : () -> Unit = {},
    backgroundColor: Color = primaryColor,
    primaryTextClicked: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .background(backgroundColor)
            .systemBarsPadding()
            .fillMaxWidth()
            .wrapContentHeight()
//            .height(70.dp)
            .padding(start = 18.dp, end = 18.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    primaryButtonClicked()
                },
            painter = painterResource(id = if (isBackButtonVisible) R.drawable.outline_keyboard_backspace_24 else R.drawable.baseline_person_24),
            contentDescription = if (isBackButtonVisible) "Back Button" else "User Image",
            tint = whiteColor
        )

        Spacer(modifier = Modifier.width(18.dp))

        TextComponent(
            modifier = Modifier.wrapContentSize()
                .clickable {
                    primaryTextClicked()
                },
            textValue = title,
            textColorValue = whiteColor,
            fontSizeValue = 20.sp,
            paddingValue = 8.dp
        )

        Spacer(modifier = Modifier.weight(1f))

        if (isNotificationButtonVisible) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                tint = whiteColor
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, )
@Composable
fun AppToolBarPreview() {
    AppToolBar(
        title = "Home",
        isBackButtonVisible = false,
        isNotificationButtonVisible = true
    )
}
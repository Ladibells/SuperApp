package dev.ladibells.design.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage

@Composable
fun ImageComponent(
    modifier: Modifier,
    resourceValue: Int? = null,
    url: String? = null
){
    resourceValue?.also {
        Image(
            modifier = modifier,
            painter = painterResource(id = it),
            contentDescription = "Icon",
//        contentScale = ContentScale.Crop
        )
    }

    url?.also {
        AsyncImage(
            modifier = modifier,
            model = it,
            contentDescription = "Banner Image",
            contentScale = ContentScale.Fit
        )
    }
}
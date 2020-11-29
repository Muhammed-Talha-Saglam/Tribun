package dev.bytecode.myapplication.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun loadLogoFromDrawable(@DrawableRes resId: Int,height: Dp, width: Dp) {

    // Fetch image from the drawable resource file asycnly

    Icon(
        asset = vectorResource(id = resId),
        modifier = Modifier
            .height(height)
            .width(width),
        tint = Color.White
    )


}
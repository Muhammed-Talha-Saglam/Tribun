package dev.bytecode.myapplication.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.Dp

@Composable
fun loadBottomNavIcon(@DrawableRes resId: Int, height: Dp, width: Dp, alpha: Float) {

    // Fetch image from the drawable resource file asycnly
    val logo = loadImageResource(id = resId)
    logo.resource.resource?.let {
        Image(
            asset = it,
            modifier = Modifier.height(height).width(width),
            alpha = alpha
            )
    }

}
package dev.bytecode.myapplication


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageResource

// Returns an image with the given resource id
@Composable
fun loadBackgroundImage(@DrawableRes resId: Int) {

    // Fetch image from the drawable resource file asycnly
    val img = loadImageResource(id = resId)
    
    img.resource.resource?.let {
        Image(
            asset = it,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
    }

}
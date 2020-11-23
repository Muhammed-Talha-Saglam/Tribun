package dev.bytecode.myapplication.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.R

@Composable
fun makeTribunLogo() {

    // Fetch image from the drawable resource file asycnly
    val logo = loadImageResource(id = R.drawable.tribun_logo)
    logo.resource.resource?.let {
        Image(
            asset = it,
            modifier = Modifier.height(40.dp).width(229.7.dp)
        )
    }

}
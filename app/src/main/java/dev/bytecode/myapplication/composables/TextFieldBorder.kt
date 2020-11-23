package dev.bytecode.myapplication.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun makeBorder() {
    Divider(
        color = Color.White,
        thickness = 1.dp,
        modifier = Modifier.width(236.dp)
    )
}
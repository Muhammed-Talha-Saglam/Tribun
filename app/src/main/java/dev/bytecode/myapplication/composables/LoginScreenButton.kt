package dev.bytecode.myapplication.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.kButtonTextStyle

@Composable
fun makeLoginScreenButton(text: String, bgColor: Color, onClick: () -> Unit ) {

    TextButton(

        modifier = Modifier
            .height(45.7.dp)
            .width(266.7.dp)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(5.dp)
            ),
        onClick = onClick
        ) {

        Text(
            text = text,
            style = kButtonTextStyle,

        )
    }

}
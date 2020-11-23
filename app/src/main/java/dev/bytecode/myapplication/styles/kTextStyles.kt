package dev.bytecode.myapplication

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val kTextFieldPlaceHolderStyle = TextStyle(
    fontSize = 15.sp,
    fontFamily = fontFamily(font(resId = R.font.neris)),
    lineHeight = 20.3.sp,
    textAlign = TextAlign.Left,
    color = Color.White,

)

val kBottomPageTextStyle = TextStyle(
    fontSize = 11.7.sp,
    fontFamily = fontFamily(font(resId = R.font.neris)),
    lineHeight = 15.7.sp,
    textAlign = TextAlign.Left,
    color = Color.White,

)

val kButtonTextStyle = TextStyle(
    fontSize = 11.7.sp,
    fontFamily = fontFamily(font(resId = R.font.neris)),
    lineHeight = 15.7.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,

)
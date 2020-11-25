package dev.bytecode.myapplication

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val defaultFontFamily = fontFamily(font(resId = R.font.neris))

val kTextFieldPlaceHolderStyle = TextStyle(
    fontSize = 15.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 20.3.sp,
    textAlign = TextAlign.Left,
    color = Color.White,

)

val kBottomPageTextStyle = TextStyle(
    fontSize = 11.7.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 15.7.sp,
    textAlign = TextAlign.Left,
    color = Color.White,

)

val kButtonTextStyle = TextStyle(
    fontSize = 11.7.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 15.7.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,
)

val kNameSurnameTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 15.7.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,
)

val kTeamNameTextStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 13.3.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,
)

val kBottomNavTextStyle = TextStyle(
    fontSize = 10.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 13.3.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,
)

val kSelectTeamTextStyle = TextStyle(
    fontSize = 13.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 18.sp,
    textAlign = TextAlign.Left,
    color = Color.Black,
)

val kTopBarTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = defaultFontFamily,
    lineHeight = 24.sp,
    textAlign = TextAlign.Left,
    color = Color.White,
)




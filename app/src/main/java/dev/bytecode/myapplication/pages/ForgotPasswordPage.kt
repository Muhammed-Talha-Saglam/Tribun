package dev.bytecode.myapplication

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel
import dev.bytecode.myapplication.viewModelClasses.UserType

@Composable
fun makeForgotPasswordPage(authViewModel: AuthenticationViewModel, activity: Activity) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Top bar
        makeTopBar(authViewModel)
        Spacer(Modifier.height(83.3.dp))


        // Text field for the e-mail
        makePasswordResetTextField(authViewModel)
        Spacer(Modifier.height(27.dp))


        // This button starts the password reset process
        makeResetButton(authViewModel, activity)

    }
}






@Composable
fun makeTopBar(authViewModel: AuthenticationViewModel) {
    Row(
        modifier = Modifier.background(color = Color.Black).height(63.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ) {


        // The arrow icon navigates back to sign-in page
        Icon(
            asset = vectorResource(id = R.drawable.ic_back_arrow),
            modifier = Modifier
                .clickable(onClick = { authViewModel.changeUsertype(UserType.ExistingUser) })
                .padding(horizontal = 30.dp, vertical = 15.dp)
                .height(18.dp)
                .width(11.dp),
            tint = Color.White
        )

        // Top bar title
        Text(
            text = stringResource(id = R.string.forgot_password_topbar),
            style = TextStyle(
                fontSize = 18.3.sp,
                fontFamily = fontFamily(font(resId = R.font.neris)),
                lineHeight = 24.7.sp,
                color = Color.White,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier.padding(start = 55.dp)
        )
    }
}


@Composable
fun makePasswordResetTextField(authViewModel: AuthenticationViewModel) {

    val fieldText by authViewModel.email.observeAsState()

    // Make email text input field
    TextField(
        value = if (fieldText.isNullOrEmpty()) "" else fieldText!!,
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontFamily = fontFamily(font(resId = R.font.neris)),
            color = Color(147, 144, 144),
            lineHeight = 20.3.sp,
            textAlign = TextAlign.Left,
        ),
        onValueChange = {
            authViewModel.setEmail(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.email_placeholder),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = fontFamily(font(resId = R.font.neris)),
                    color = Color(147, 144, 144),
                    lineHeight = 20.3.sp,
                    textAlign = TextAlign.Left,
                ),
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        onImeActionPerformed = { ime, controler ->
            controler?.hideSoftwareKeyboard()
        },
        backgroundColor = Color.Transparent,
        activeColor = Color(147, 144, 144),
        inactiveColor = Color.Transparent,
        modifier = Modifier
            .height(50.dp)
            .width(266.7.dp)
            .border(width = 0.7.dp, color = Color.Black, shape = RoundedCornerShape(13.7.dp))

    )
}

@Composable
fun makeResetButton(authViewModel: AuthenticationViewModel, activity: Activity) {
    TextButton(
        modifier = Modifier.fillMaxWidth()
            .height(45.7.dp)
            .width(266.7.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            ),
        onClick = { authViewModel.resetPassword(activity) }
    ) {

        Text(
            text = stringResource(id = R.string.reset),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = fontFamily(font(resId = R.font.neris)),
                lineHeight = 20.3.sp,
                textAlign = TextAlign.Left,
                color = Color.White
            )
        )
    }
}
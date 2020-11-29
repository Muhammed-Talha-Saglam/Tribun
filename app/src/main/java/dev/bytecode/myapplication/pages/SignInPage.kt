package dev.bytecode.myapplication


import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.composables.makeBorder
import dev.bytecode.myapplication.composables.makeLoginScreenButton
import dev.bytecode.myapplication.utils.loadLogoFromDrawable
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel
import dev.bytecode.myapplication.viewModelClasses.UserType


// This page is for the existing user to sign in.
@Composable
fun makeSignInPage(
    authViewModel: AuthenticationViewModel,
    activity: Activity,
    navigation: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize().border(color = Color.Black, width = 1.dp)) {

        // Background image
        loadBackgroundImage(resId = R.drawable.bg_ronaldo)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(80.dp))

            // Tribun logo
            Image(asset = imageResource(id = R.drawable.tribun_logo), modifier = Modifier.height(40.dp).width(280.dp))
            Spacer(modifier = Modifier.size(110.dp))

            // Make e-mail/password fields and button
            makeSignInFields(
                authViewModel = authViewModel,
                activity = activity,
                navigation = navigation
            )

        }

        // Bottom texts
        // User navigates to the sign-up page or forgot password page by clicking here
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            makeSignInPageBottom(authViewModel = authViewModel)
        }

    }
}







@Composable
fun makeSignInFields(
    authViewModel: AuthenticationViewModel,
    activity: Activity,
    navigation: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Make email text input field
        makeTextInputField(
            value = authViewModel.email,
            placeholder = stringResource(id = R.string.email_placeholder),
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Email,
            onChange = { authViewModel.setEmail(it) }
        )

        // Draw an bottom border
        makeBorder()


        // Make password text input field
        makeTextInputField(
            value = authViewModel.password,
            placeholder = stringResource(id = R.string.password_placeholder),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            onChange = { authViewModel.setPassword(it) }
        )

        // Draw an bottom border
        makeBorder()

        Spacer(modifier = Modifier.height(33.8.dp))

        // The button to be pressed when the user wants to sign in.
        makeLoginScreenButton(
            text = stringResource(id = R.string.sign_in),
            bgColor = Color.White,
            onClick = { authViewModel.signInUser(activity, navigation) })
    }


}


@Composable
fun makeSignInPageBottom(authViewModel: AuthenticationViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {

        // If it is a new user, he navigates to sign-up page by clicking here
        Text(
            text = stringResource(id = R.string.create_new_account),
            style = kBottomPageTextStyle,
            modifier = Modifier.clickable(
                onClick = { authViewModel.changeUsertype(UserType.NewUser) }
            ).padding(
                bottom = 35.dp,
                start = 30.dp
            )
        )

        // If the user forgets his/her password, he navigates to forgot-password page by clicking here
        Text(
            text = stringResource(id = R.string.forgot_password),
            style = kBottomPageTextStyle,
            modifier = Modifier.clickable(
                onClick = { authViewModel.changeUsertype(UserType.ForgotPasswordUser) }
            ).padding(
                bottom = 35.dp,
                end = 30.dp
            )
        )
    }
}

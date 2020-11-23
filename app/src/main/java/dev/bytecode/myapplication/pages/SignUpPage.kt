package dev.bytecode.myapplication

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.composables.makeBorder
import dev.bytecode.myapplication.composables.makeLoginScreenButton
import dev.bytecode.myapplication.utils.makeTribunLogo
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel
import dev.bytecode.myapplication.viewModelClasses.UserType

@Composable
fun makeSignUpPage(
    authViewModel: AuthenticationViewModel,
    activity: Activity,
    navigation: () -> Unit
) {


    Box(modifier = Modifier.fillMaxSize().border(color = Color.Black, width = 1.dp)) {

        // Background image
        loadImage(resId = R.drawable.bg_messi)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.size(80.dp))

            // Tribun logo
            makeTribunLogo()

            Spacer(modifier = Modifier.size(105.dp))

            // Make username, e-mail, password fields and button
            makeSignUpFields(
                authViewModel = authViewModel,
                activity = activity,
                navigation = navigation
            )

        }

        // Bottom text
        // User navigates back to the sign-in page by clicking here
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.already_have_account),
                style = kBottomPageTextStyle,
                modifier = Modifier.clickable(
                    onClick = { authViewModel.changeUsertype(UserType.ExistingUser) }
                ).padding(bottom = 35.dp)
            )
        }

    }

}


@Composable
fun makeSignUpFields(
    authViewModel: AuthenticationViewModel,
    activity: Activity,
    navigation: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Make username text input field
        makeTextInputField(
            value = authViewModel.userName,
            placeholder = stringResource(id = R.string.name_surname),
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Text,
            onChange = { authViewModel.setUserName(it) }
        )

        // Draw an bottom border
        makeBorder()


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
            placeholder = stringResource(id = R.string.password_placeholder2),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            onChange = { authViewModel.setPassword(it) }
        )

        // Draw an bottom border
        makeBorder()

        Spacer(modifier = Modifier.height(33.8.dp))

        makeLoginScreenButton(
            text = stringResource(id = R.string.sign_up),
            bgColor = Color.White,
            onClick = { authViewModel.signUpNewUser(activity, navigation) })
    }

}
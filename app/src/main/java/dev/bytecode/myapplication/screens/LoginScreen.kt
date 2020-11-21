package dev.bytecode.myapplication.screens

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import dev.bytecode.myapplication.makeForgotPasswordPage
import dev.bytecode.myapplication.makeSignInPage
import dev.bytecode.myapplication.makeSignUpPage
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel
import dev.bytecode.myapplication.viewModelClasses.UserType

@Composable
fun MakeLoginScreen(authViewModel: AuthenticationViewModel, activity: Activity, goToHomePage: () -> Unit, goToTeamSelectPage: () -> Unit) {


    // When the user type changes, login screen also changes accordingly.
    // "remember" keyword creates an internal state,
    // when the state changes, this function recomposes.
    var userType = authViewModel.userType.observeAsState()
    val state = remember { userType }



    // UI of the Login screen changes, according to the user type.
    // Crossfade enables a smooth transition between pages.
    Crossfade(current = state.value, animation = tween(1000)) { userType ->
        when(userType) {
            UserType.ExistingUser -> makeSignInPage(authViewModel = authViewModel, activity, goToHomePage)
            UserType.NewUser -> makeSignUpPage(authViewModel = authViewModel, activity, goToTeamSelectPage)
            UserType.ForgotPasswordUser -> makeForgotPasswordPage( authViewModel= authViewModel, activity)
            else -> null
        }
    }


}


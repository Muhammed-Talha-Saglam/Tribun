package dev.bytecode.myapplication.pages

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.composables.makeTextInputField
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel

@Composable
fun LoginPage(activity: Activity, navigation: () -> Unit) {

    // We manage the state of the login page in AuthenticationViewModel class.
    // Authentication processes are also done there.
    val authViewModel: AuthenticationViewModel = viewModel()

    // When the user type changes, login screen also changes accordingly
    // remember keyword creates an internal state,
    // when the state changes, this function recomposes
    var userType = authViewModel.userType.observeAsState()
    val state = remember { userType }


    // This function returns a login screen with input fields.
    // This is a temporary screen for the testing of AuthenticationViewModel
    // and will be deleted in the future.
    makeTempLoginScreen(authViewModel, activity, navigation)

    //TODO: Make Login Screen

}



@Composable
fun makeTempLoginScreen(vm: AuthenticationViewModel, activity: Activity, navigation: () -> Unit) {

    // This column and its children are temporary
    // Used for the testing of authentication processes
    // Will its content in the future
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {



        makeTextInputField(
            value = vm.userName,
            placeholder = "Kullanıcı Adı",
            keyboardType = KeyboardType.Text,
            onChange = {
                vm.setUserName(it)
            })

        makeTextInputField(
            value = vm.email,
            placeholder = "E-mail",
            keyboardType = KeyboardType.Email,
            onChange = {
                vm.setEmail(it)
            })

        makeTextInputField(
            value = vm.password,
            placeholder = "Şifre",
            keyboardType = KeyboardType.Password,
            onChange = {
                vm.setPassword(it)
            })


        Button(onClick = {
            vm.signUpNewUser(activity, navigation)
        }) {
            Text(text = "KAYDOL")
        }

        Button(onClick = {
            vm.signInUser(activity, navigation)
        }) {
            Text(text = "GİRİŞ YAP")
        }


        Button(onClick = {
            vm.signOut()
        }) {
            Text(text = "ÇIKIŞ YAP")
        }


        Button(onClick = {
            vm.resetPassword(activity)
        }) {
            Text(text = "Şifreyi Yenile")
        }

    }
}



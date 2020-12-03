package dev.bytecode.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import dev.bytecode.myapplication.HomeScreenActivity
import dev.bytecode.myapplication.SelectTeamActivity
import dev.bytecode.myapplication.screens.MakeLoginScreen
import dev.bytecode.myapplication.ui.MyApplicationTheme
import dev.bytecode.myapplication.viewModelClasses.AuthenticationViewModel


class MainActivity : AppCompatActivity() {


    // We manage the state of the login page in AuthenticationViewModel class.
    // Authentication processes are also done there.
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        // The user haven't signed out, directly navigate to Home Screen.
        if (authViewModel.currentUser != null) {
            goToHomePage()
        }

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TribunApp(authViewModel,this, {goToHomePage()}, {goToSelectTeamPage()} )
                }
            }
        }
    }


    // When the sign-in is successful, navigate the user to home screen
    private fun goToHomePage() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }


    // When the sign-up is successful, navigate the user to select team screen
    private fun goToSelectTeamPage() {
        val intent = Intent(this, SelectTeamActivity::class.java)
        startActivity(intent)
        finish()
    }

}


@Composable
fun TribunApp(authViewModel: AuthenticationViewModel, activity: Activity, goToHomePage: () -> Unit, goToTeamSelectPage: () -> Unit) {

    // This screen shows username, e-mail, and password fields
    MakeLoginScreen(authViewModel, activity, goToHomePage, goToTeamSelectPage)


}



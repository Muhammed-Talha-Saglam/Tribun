package dev.bytecode.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import dev.bytecode.myapplication.activities.ui.SelectTeamActivity
import dev.bytecode.myapplication.pages.LoginScreen
import dev.bytecode.myapplication.ui.MyApplicationTheme


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TribunApp(this, {goToHomePage()}, {goToSelectTeamPage()} )
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
fun TribunApp(activity: Activity, goToHomePage: () -> Unit,goToTeamSelectPage: () -> Unit) {


      // This screen shows username, e-mail, and password fields
      LoginScreen(activity, goToHomePage, goToTeamSelectPage)


}



package dev.bytecode.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import dev.bytecode.myapplication.pages.LoginPage
import dev.bytecode.myapplication.ui.MyApplicationTheme


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TribunApp(this ) { goToHomePage()}
                }
            }
        }
    }


    // When the login is successful, navigate the user to home screen
    private fun goToHomePage() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

}


@Composable
fun TribunApp(activity: Activity, navigation: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize(), alignment = Alignment.Center) {

      // This page shows username, e-mail, and password fields
      LoginPage(activity, navigation)

    }
}



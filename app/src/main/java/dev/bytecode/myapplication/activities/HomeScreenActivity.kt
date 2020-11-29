package dev.bytecode.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import dev.bytecode.myapplication.MakeHomeScreen
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(this)
                }
            }
        }
    }

}

@Composable
fun HomeScreen(activity: Activity) {

    MakeHomeScreen(activity)

}

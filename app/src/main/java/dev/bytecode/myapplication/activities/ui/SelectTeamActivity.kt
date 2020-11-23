package dev.bytecode.myapplication.activities.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import dev.bytecode.myapplication.activities.ui.ui.MyApplicationTheme

class SelectTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2()
                }
            }
        }
    }
}

@Composable
fun Greeting2() {
    Text(text = "THIS IS THE TEAM SELECT PAGE")
    // TODO: MAKE TEAM SELECT PAGE SCREEN
}



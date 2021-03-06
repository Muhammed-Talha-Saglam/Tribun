package dev.bytecode.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.rememberNavController
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel


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

    val viewModel = viewModel(modelClass = DatabaseViewModel::class.java)
    viewModel.getCurrentUser()

    MakeHomeScreen(activity, viewModel)

}

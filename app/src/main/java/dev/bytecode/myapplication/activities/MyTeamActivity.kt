package dev.bytecode.myapplication.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.R
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.composables.makeWebViewTopBar
import dev.bytecode.myapplication.kSelectTeamTextStyle
import dev.bytecode.myapplication.utils.MyWebViewClient
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel
import dev.bytecode.myapplication.viewModelClasses.WebViewViewModel

class MyTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyTeamPage(this)
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyTeamPage(activity: Activity) {

    val databaseViewModel = viewModel(modelClass = DatabaseViewModel::class.java)
    databaseViewModel.getCurrentUser()
    val team = databaseViewModel.supportingTeam.observeAsState()


    val webViewModel = viewModel(modelClass = WebViewViewModel::class.java)
    val isLoading by webViewModel.isLoading.observeAsState()
    webViewModel.isLoading.value = true



    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.height(63.3.dp).fillMaxWidth(),
                elevation = 0.dp,
                title = {

                    makeWebViewTopBar(resId = R.string.my_team_topbar, activity = activity)


                },

            )
        }
    ) {


        team.let {

            // The user has not chosen a team, warn him
            if (it.value?.id == "other") {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Lütfen takip etmek istediğiniz takımı",
                        style = kSelectTeamTextStyle
                    )
                    Text(
                        text = "profilinizdeki takımım alanından seçiniz.",
                        style = kSelectTeamTextStyle
                    )

                }

            } else {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center
                ) {


                    AndroidView(
                        viewBlock = ::WebView,
                        modifier = Modifier.fillMaxSize()
                    ) { webView ->

                        with(webView) {

                            settings.javaScriptEnabled = true
                            webViewClient = MyWebViewClient(webViewModel)
                            it.value?.infoUrl?.let { it1 -> loadUrl(it1) }

                        }

                    }


                    AndroidView(viewBlock = ::ProgressBar) { progressBar ->

                        if (!isLoading!!) {
                            progressBar.visibility = View.GONE
                        }

                    }
                }

            }
        }


    }

}




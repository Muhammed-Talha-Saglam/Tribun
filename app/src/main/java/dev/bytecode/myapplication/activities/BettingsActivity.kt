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
import dev.bytecode.myapplication.utils.MyWebViewClient
import dev.bytecode.myapplication.viewModelClasses.WebViewViewModel

class BettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    BettingsPage(this)
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BettingsPage(activity: Activity) {


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

                    makeWebViewTopBar(resId = R.string.bettings_topbar, activity = activity)
                },
                )
        }
    ) {


        Box(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center
        ) {


            AndroidView(viewBlock = ::WebView, modifier = Modifier.fillMaxSize()) { webView ->

                with(webView) {
                    settings.javaScriptEnabled = true
                    webViewClient = MyWebViewClient(webViewModel)
                    settings.domStorageEnabled = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    loadUrl("https://www.iddaa.com/program/futbol")
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


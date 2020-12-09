package dev.bytecode.myapplication.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.R
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.composables.makeWebViewTopBar
import dev.bytecode.myapplication.utils.MyWebViewClient
import dev.bytecode.myapplication.utils.loadLogoFromDrawable
import dev.bytecode.myapplication.viewModelClasses.WebViewViewModel

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RankingPage(this)
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun RankingPage(activity: Activity) {


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
                    makeWebViewTopBar(resId = R.string.rankings_topbar, activity = activity)
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
                    loadUrl("https://www.google.com/search?sxsrf=ALeKk01RFYiH9pzjUPhHjqhs-MT4T_JVkQ%3A1606382205689&ei=fXK_X9bTKY2xUMbegrAI&q=puan+durumu+s%C3%BCper+lig&gs_ssp=eJzj4tTP1TcwKbK0KDJg9BIrKE3MU0gpLSrNLVUoPrynILVIISczHQDGeQxU&oq=puan+durumu+&gs_lcp=CgZwc3ktYWIQARgAMggILhCxAxCTAjICCAAyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADoECAAQR1C6C1i6C2DoGWgAcAJ4AIABaIgBaJIBAzAuMZgBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=psy-ab#sie=lg;/g/11j376fdx9;2;/m/04r98r;st;fp;1;;")
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


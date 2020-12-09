package dev.bytecode.myapplication.pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.utils.MyWebViewClient
import dev.bytecode.myapplication.viewModelClasses.WebViewViewModel


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MakeNewsPage() {


    val viewModel = viewModel(modelClass = WebViewViewModel::class.java)
    val isLoading by viewModel.isLoading.observeAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center
    ) {


        AndroidView(viewBlock = ::WebView, modifier = Modifier.fillMaxSize()) { webView ->

            with(webView) {

                settings.javaScriptEnabled = true
                webViewClient = MyWebViewClient(viewModel)
                loadUrl("https://onedio.com/spor")

            }

        }



        AndroidView(viewBlock = ::ProgressBar) { progressBar ->

            if (!isLoading!!) {
                progressBar.visibility = View.GONE
            }

        }

    }

}


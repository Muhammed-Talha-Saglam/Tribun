package dev.bytecode.myapplication.pages

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MakeNewsPage() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Box(
            alignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Black)
        }

        AndroidView(viewBlock = ::WebView) { webView ->
            with(webView) {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl("https://onedio.com/spor")
            }

        }
    }

}
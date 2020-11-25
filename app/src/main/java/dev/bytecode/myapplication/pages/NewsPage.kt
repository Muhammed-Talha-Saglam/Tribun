package dev.bytecode.myapplication.pages

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MakeNewsPage() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(viewBlock = ::WebView) { webView ->
            with(webView) {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl("https://m.sporx.com/")
            }

        }
    }

}
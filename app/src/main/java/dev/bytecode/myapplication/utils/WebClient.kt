package dev.bytecode.myapplication.utils

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import dev.bytecode.myapplication.viewModelClasses.WebViewViewModel

class MyWebViewClient(viewModel: WebViewViewModel): WebViewClient() {

    val vm = viewModel

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        //    return super.shouldOverrideUrlLoading(view, url)
        view.loadUrl(url)
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        vm.isLoading.value = false
        super.onPageFinished(view, url)
    }

}
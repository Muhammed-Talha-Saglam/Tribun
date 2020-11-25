package dev.bytecode.myapplication.activities

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import dev.bytecode.myapplication.R
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.loadLogoFromDrawable

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

@Composable
fun BettingsPage(activity: Activity) {


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.height(63.3.dp),
                elevation = 0.dp,
                title = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.width(60.dp))
                        Text(
                            text = stringResource(id = R.string.bettings_topbar),
                            color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            activity.finish()
                        }
                    ) {
                        Icon(asset = Icons.Default.ArrowBack, tint = Color.White)
                    }
                },
            )
        }
    ) {

        AndroidView(viewBlock = ::WebView) { webView ->

            with(webView) {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl("https://www.iddaa.com/program/futbol")
            }

        }

    }


}


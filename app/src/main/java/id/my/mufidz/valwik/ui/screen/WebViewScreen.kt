package id.my.mufidz.valwik.ui.screen

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.ui.components.BaseScaffold

@Composable
@Destination
fun WebViewScreen(
    navigator: DestinationsNavigator,
    title: String = "-",
    url: String = "https://playvalorant.com/id-id/"
) {
    var isLoading by remember { mutableStateOf(true) }
    var webView: WebView? = null

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            navigator.navigateUp()
        }
    }

    BaseScaffold(title = title, onBackBtn = {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            navigator.navigateUp()
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = isLoading, modifier = Modifier.fillMaxWidth()
            ) {
                LinearProgressIndicator()
            }
            AndroidView(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
                factory = {
                    WebView(it).apply {
                        webView = this
                        webViewClient = WebViewClient()
                        loadUrl(url)
                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView, url: String?, favicon: Bitmap?
                            ) {
                                isLoading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                            }
                        }
                    }
                })
        }
    }
}
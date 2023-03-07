package id.my.mufidz.valwik.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import id.my.mufidz.valwik.BuildConfig
import id.my.mufidz.valwik.ui.screen.settings.components.PreferenceContentItem

@Composable
fun AboutScreen() {
    AboutScreenContent()
}

@Composable
@Preview(showBackground = true)
private fun AboutScreenContent() {
    val uriHandler = LocalUriHandler.current
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
        PreferenceContentItem(
            title = "Version",
            description = "v${BuildConfig.VERSION_NAME}"
        )
        PreferenceContentItem(
            title = "Github",
            description = "View the source code",
            onClick = {
                uriHandler.openUri("https://github.com/Mufiidz/Valwik/")
            }
        )
    }
}
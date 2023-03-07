package id.my.mufidz.valwik.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun FontScreen(title: String = "TITLE") {
    val typo = MaterialTheme.typography
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Text(text = "Display Large", style = typo.displayLarge)
        Text(text = "Display Medium", style = typo.displayMedium)
        Text(text = "Display Small", style = typo.displaySmall)
        Text(text = "Headline Large", style = typo.headlineLarge)
        Text(text = "Headline Medium", style = typo.headlineMedium)
        Text(text = "Headline Small", style = typo.headlineSmall)
        Text(text = "Title Large", style = typo.titleLarge)
        Text(text = "Title Medium", style = typo.titleMedium)
        Text(text = "Title Small", style = typo.titleSmall)
        Text(text = "Body Large", style = typo.bodyLarge)
        Text(text = "Body Medium", style = typo.bodyMedium)
        Text(text = "Body Small", style = typo.bodySmall)
        Text(text = "Default")
        Text(text = "Label Large", style = typo.labelLarge)
        Text(text = "Label Medium", style = typo.labelMedium)
        Text(text = "Label Small", style = typo.labelSmall)
    }
}
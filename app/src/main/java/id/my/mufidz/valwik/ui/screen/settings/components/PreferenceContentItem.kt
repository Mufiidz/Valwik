package id.my.mufidz.valwik.ui.screen.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.textSecondary

@Composable
@Preview(showBackground = true)
fun PreferenceContentItem(
    modifier: Modifier = Modifier,
    title: String = "TITLE",
    description: String = "Description",
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
    trailingContent: (@Composable () -> Unit)? = null
) {
    val cleanTitle = title.ifEmpty { "TITLE" }
    val cleanDesc = description.ifEmpty { "DESCRIPTION" }
    val (typo,_,_,spacing) = MaterialTheme.style

    Row(
        modifier
            .clickable(enabled = isEnabled, onClick = onClick)
            .alpha(if (isEnabled) 1f else 0.5f)
            .padding(horizontal = spacing.small, vertical = spacing.medium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = cleanTitle,
                style = typo.bodyMedium,
            )

            Text(text = cleanDesc, style = TextStyle(textSecondary))
        }
        trailingContent?.invoke()
    }
}
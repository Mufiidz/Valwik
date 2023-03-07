package id.my.mufidz.valwik.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.text.color
import id.my.mufidz.valwik.ui.style.textSecondary

@Composable
fun NavRail(modifier: Modifier = Modifier, contents: List<RailData>, current: (Int) -> Unit) {
    var selectedItem by remember { mutableStateOf(0) }
    NavigationRail(
        modifier = modifier, containerColor = MaterialTheme.colorScheme.background
    ) {
        Spacer(Modifier.weight(1f))
        contents.forEachIndexed { index, railData ->
            RailItem(selectedItem == index, railData) {
                selectedItem = index
                current.invoke(selectedItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RailItem(
    selected: Boolean = false,
    railData: RailData = RailData(),
    onCLick: () -> Unit = {},
) {
    val (typo, color, _, spacing) = MaterialTheme.style
    val transition = updateTransition(targetState = selected, label = null)

    val textColor by transition.animateColor(label = "") {
        if (it) color.onBackground else textSecondary
    }
    Column(
        Modifier
            .rotate(-90f)
            .selectable(selected = selected, onClick = onCLick, role = Role.Tab)
            .padding(vertical = spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = railData.icon,
            contentDescription = "",
            Modifier.alpha(if (selected) 1f else 0f)
        )
        Text(text = railData.title.takeIf { it.isNotEmpty() } ?: "TITLE",
            modifier = Modifier.padding(vertical = spacing.small),
            style = typo.labelMedium.color(textColor)
        )
    }
}

data class RailData(
    val title: String = "TITLE",
    val icon: ImageVector = Icons.Default.Clear,
    var screen: @Composable () -> Unit = { }
)
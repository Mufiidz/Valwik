package id.my.mufidz.valwik.ui.screen.settings.components

import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun SwitchPreference(
    title: String = "TITLE",
    description: String = "DESCRIPTION",
    isChecked: Boolean = false,
    onClicked: (Boolean) -> Unit = {}
) {
    var isSwitchChecked by remember {
        mutableStateOf(isChecked)
    }
    PreferenceContentItem(
        title = title,
        description = description,
        onClick = {
            isSwitchChecked = !isSwitchChecked
            onClicked(isSwitchChecked)
        },
    ) {
        Switch(checked = isSwitchChecked, onCheckedChange = { isChecked ->
            isSwitchChecked = isChecked
            onClicked(isSwitchChecked)
        })
    }

}
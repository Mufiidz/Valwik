package id.my.mufidz.valwik.ui.screen.settings.components

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import id.my.mufidz.valwik.ui.components.ValueSelectorDialog
import timber.log.Timber

@Composable
@Preview(showBackground = true)
fun ListPreference(
    title: String = "TITLE",
    list: List<String> = listOf(),
    selectedValue: String = "",
    onSelectedValue : (String) -> Unit = {}
) {
    val desc = selectedValue.ifEmpty { list[0].ifEmpty { "DESCRIPTION" } }

    var isShowingDialog by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf(desc)
    }

    PreferenceContentItem(
        title = title,
        description = selected,
        onClick = { isShowingDialog = true }
    )

    if (isShowingDialog) {
        ValueSelectorDialog(title, list, selected, {
            isShowingDialog = false
        }) {
            Timber.d(it)
            selected = it
            onSelectedValue(it)
        }
    }
}
package id.my.mufidz.valwik.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.text.align
import id.my.mufidz.valwik.ui.style.text.color

@Composable
@Preview(showBackground = true)
fun ValueSelectorDialog(
    title: String = "TITLE",
    values: List<String> = listOf("EXAMPLE"),
    selectedValue: String = "TITLE",
    onDismiss: () -> Unit = {},
    onSelectedValue: (String) -> Unit = {}
) {
    val (typo, color, shape, spacing) = MaterialTheme.style
    Dialog(onDismissRequest = onDismiss) {
        ConstraintLayout(
            Modifier
                .background(color.surface, shape.medium)
                .padding(spacing.medium)
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            val (titleView, valuesView, footerView) = createRefs()
            Text(
                text = title,
                modifier = Modifier.constrainAs(titleView) {
                    top.linkTo(parent.top, margin = spacing.small)
                    linkTo(
                        parent.start,
                        parent.end,
                        startMargin = spacing.small,
                        endMargin = spacing.small,
                        bias = 0f

                    )
                },
                style = typo.headlineSmall,
            )
            Column(Modifier.constrainAs(valuesView) {
                top.linkTo(titleView.bottom, margin = spacing.small)
                linkTo(
                    parent.start,
                    parent.end,
                    startMargin = spacing.small,
                    endMargin = spacing.small
                )
            }) {
                values.forEach { value ->
                    ItemValueSelector(value, selectedValue) {
                        onSelectedValue(it)
                        onDismiss.invoke()
                    }
                }
            }
            TextButton(onClick = onDismiss, Modifier.constrainAs(footerView) {
                linkTo(valuesView.bottom, parent.bottom, topMargin = spacing.medium)
                linkTo(
                    parent.start,
                    parent.end,
                    startMargin = spacing.small,
                    endMargin = spacing.small,
                    bias = 1f
                )
            }) {
                Text(
                    text = "Cancel",
                    style = typo.labelLarge.color(color.primary)
                )
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
private fun ItemValueSelector(
    title: String = "TITLE", selectedValue: String = "TITLE", onSelectedValue: (String) -> Unit = {}
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .selectable(selected = (title == selectedValue), onClick = { onSelectedValue(title) })
    ) {
        val (radio, text) = createRefs()

        createHorizontalChain(radio, text, chainStyle = ChainStyle.Packed(0f))

        RadioButton(selected = (title == selectedValue),
            onClick = { onSelectedValue(title) },
            Modifier.constrainAs(radio) {
                linkTo(parent.top, parent.bottom)
            })
        Text(text = title,
            style = MaterialTheme.typography.bodyMedium.align(TextAlign.Start),
            modifier = Modifier
                .constrainAs(text) {
                    linkTo(radio.top, radio.bottom)
                    width = Dimension.fillToConstraints
                }
                .padding(end = 8.dp))
    }
}


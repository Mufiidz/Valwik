package id.my.mufidz.valwik.ui.screen.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.text.color

@Composable
@Preview(showBackground = true)
fun PreferenceCategory(
    modifier: Modifier = Modifier,
    title: String = "TITLE",
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val (typo, color, _, spacing) = MaterialTheme.style
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = spacing.small)
    ) {
        val (titleText, contents) = createRefs()

        Text(
            text = title.uppercase(),
            style = typo.bodyLarge.color(color.primary),
            modifier = modifier
                .constrainAs(titleText) {
                    top.linkTo(parent.top, margin = spacing.small)
                    linkTo(parent.start, parent.end, bias = 0f)
                }
        )
        Column(Modifier.constrainAs(contents) {
            linkTo(
                titleText.bottom,
                parent.bottom,
                bias = 0f,
                topMargin = spacing.small,
                bottomMargin = spacing.small
            )
            linkTo(parent.start, parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }, content = content)
    }
}

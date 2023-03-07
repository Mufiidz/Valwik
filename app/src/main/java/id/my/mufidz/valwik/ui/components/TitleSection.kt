package id.my.mufidz.valwik.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.text.light
import id.my.mufidz.valwik.ui.style.text.medium
import id.my.mufidz.valwik.utils.shimmer

@Preview(showBackground = true)
@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String = "TITLE",
    desc: String? = null,
    onClick: (() -> Unit)? = null
) {
    val (typo, color, _, spacing) = MaterialTheme.style

    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .padding(spacing.medium)
    ) {
        val (titleId, descId, seeAllId) = createRefs()

        Text(
            modifier = Modifier.constrainAs(titleId) {
                linkTo(parent.start, seeAllId.start)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            },
            text = title,
            style = typo.titleLarge.medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (desc != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(descId) {
                        top.linkTo(titleId.bottom)
                        linkTo(parent.start, seeAllId.start)
                        width = Dimension.fillToConstraints
                    },
                text = desc,
                style = typo.bodySmall.light,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (onClick != null) {
            Text(
                text = "Lihat Semua",
                modifier = Modifier
                    .clickable { onClick() }
                    .constrainAs(descId) {
                        linkTo(parent.top, parent.bottom)
                        end.linkTo(parent.end)
                    },
                fontWeight = FontWeight.Bold,
                color = color.primary
            )
        }
    }
}

@Composable
fun TitleSectionShimmer() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(25.dp)
                .shimmer()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .shimmer()
        )
    }
}
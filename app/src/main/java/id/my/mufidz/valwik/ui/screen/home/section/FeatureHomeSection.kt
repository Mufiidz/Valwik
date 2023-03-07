package id.my.mufidz.valwik.ui.screen.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.Product
import kotlinx.coroutines.launch

@Composable
fun FeatureHomeSection(
    products: List<Product>,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(150.dp, 200.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(products) {
            ItemFeatureHome(it) {
                if (it.route.isNotEmpty()) {
                    navigator.navigate(it.route)
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Coming Soon ${it.title}")
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemFeatureHome(product: Product, onClick: () -> Unit = {}) {
    val color = MaterialTheme.colorScheme
    ConstraintLayout(
        Modifier
            .width(100.dp)
            .wrapContentHeight()
            .clickable { onClick() }) {

        val (icon, title) = createRefs()

        Box(
            Modifier
                .size(50.dp)
                .background(color.secondaryContainer, MaterialTheme.shapes.small)
                .constrainAs(icon) {
                    linkTo(parent.start, parent.end)
                    top.linkTo(parent.top, margin = 8.dp)
                })
        Text(text = product.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(title) {
                linkTo(parent.start, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                linkTo(icon.bottom, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            })
    }
}
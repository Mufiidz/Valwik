package id.my.mufidz.valwik.ui.screen.home.section

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.Maps
import id.my.mufidz.valwik.ui.components.ItemPool
import id.my.mufidz.valwik.ui.components.TitleSection
import id.my.mufidz.valwik.ui.screen.destinations.FullMapsScreenDestination
import id.my.mufidz.valwik.ui.style.LocalStyle

@Composable
fun MapsSection(maps: List<Maps>, navigator: DestinationsNavigator) {
    Column(Modifier.fillMaxWidth()) {
        TitleSection(title = "Maps") {
            navigator.navigate(FullMapsScreenDestination(listMap = maps.toTypedArray()))
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(maps.take(9)) {
                ItemMap(it)
            }
            item {
                ItemPool(maps) {
                    navigator.navigate(FullMapsScreenDestination(listMap = maps.toTypedArray()))
                }
            }
        }
    }
}

@Composable
private fun ItemMap(map: Maps = Maps()) {
    val (_, _, shapes, spacing, elevation) = LocalStyle.current
    Card(
        Modifier
            .width(300.dp)
            .padding(bottom = spacing.small, end = spacing.small),
        shape = shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.medium)
    ) {
        Box(Modifier.align(Alignment.CenterHorizontally)) {
            SubcomposeAsyncImage(
                model = map.splash,
                contentDescription = "Image Map",
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = map.displayName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 2,
                color = Color.White,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
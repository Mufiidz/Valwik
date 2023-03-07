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
import id.my.mufidz.valwik.models.WeaponBundle
import id.my.mufidz.valwik.ui.components.ItemPool
import id.my.mufidz.valwik.ui.components.TitleSection
import id.my.mufidz.valwik.ui.screen.destinations.FullWeaponBundleScreenDestination
import id.my.mufidz.valwik.ui.style.LocalStyle
import id.my.mufidz.valwik.utils.shimmer

@Composable
fun WeaponBundlesSection(weaponBundles: List<WeaponBundle>, navigator: DestinationsNavigator) {
    Column(Modifier.fillMaxWidth()) {
        TitleSection(title = "Bundle") {
            navigator.navigate(FullWeaponBundleScreenDestination(weaponBundles.toTypedArray()))
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(weaponBundles.take(9)) {
                ItemBundle(it, Modifier.padding(bottom = 8.dp, end = 8.dp))
            }
            item {
                ItemPool(weaponBundles) {
                    navigator.navigate(
                        FullWeaponBundleScreenDestination(
                            weaponBundles.toTypedArray()
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ItemBundle(weaponBundle: WeaponBundle, modifier: Modifier = Modifier) {
    val (_, _, shapes, spacing, elevation) = LocalStyle.current
    Card(
        modifier.width(400.dp),
        shape = shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.medium)
    ) {
        Box(Modifier.align(Alignment.CenterHorizontally)) {
            SubcomposeAsyncImage(
                model = weaponBundle.displayIcon,
                contentDescription = "Image Bundle",
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .shimmer()
                    )
                })
            Column(
                Modifier.padding(
                    top = spacing.medium, start = spacing.small, end = spacing.small
                )
            ) {
                Text(
                    text = weaponBundle.displayName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 2,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Collection",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
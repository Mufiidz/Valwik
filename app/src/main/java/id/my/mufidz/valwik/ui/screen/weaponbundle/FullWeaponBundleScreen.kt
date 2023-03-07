package id.my.mufidz.valwik.ui.screen.weaponbundle

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.WeaponBundle
import id.my.mufidz.valwik.ui.components.BaseScaffold
import id.my.mufidz.valwik.ui.screen.home.section.ItemBundle
import id.my.mufidz.valwik.ui.style.spacing

@Composable
@Destination
fun FullWeaponBundleScreen(navigator: DestinationsNavigator, weaponBundles: Array<WeaponBundle>) {
    val spacing = MaterialTheme.spacing
    BaseScaffold(title = "Bundles", navigator) { paddingValues ->
        LazyColumn(
            Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                bottom = spacing.small,
                start = spacing.medium,
                end = spacing.medium
            )
        ) {
            items(weaponBundles) {
                ItemBundle(it, Modifier.padding(bottom = spacing.small))
            }
        }
    }
}
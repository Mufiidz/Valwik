package id.my.mufidz.valwik.ui.screen.weapon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.weapon.Weapon
import id.my.mufidz.valwik.ui.components.BaseScaffold
import id.my.mufidz.valwik.ui.screen.home.section.ItemWeapon
import id.my.mufidz.valwik.ui.style.spacing

@Composable
@Destination
fun FullWeaponScreen(navigator: DestinationsNavigator, weapons: Array<Weapon>) {
    val spacing = MaterialTheme.spacing
    BaseScaffold(title = "Weapons", navigator) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                bottom = spacing.medium,
                start = spacing.medium,
                end = spacing.medium
            )
        ) {
            items(weapons) {
                ItemWeapon(weapon = it)
            }
        }
    }
}
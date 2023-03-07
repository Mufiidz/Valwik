package id.my.mufidz.valwik.ui.screen.home.section

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.weapon.Weapon
import id.my.mufidz.valwik.ui.components.ItemPool
import id.my.mufidz.valwik.ui.components.TitleSection
import id.my.mufidz.valwik.ui.screen.destinations.FullWeaponScreenDestination
import java.util.*

@Composable
fun WeaponsSection(weapons: List<Weapon>, navigator: DestinationsNavigator) {
    Column(Modifier.fillMaxWidth()) {
        TitleSection(title = "Weapons") {
            navigator.navigate(FullWeaponScreenDestination(weapons.toTypedArray()))
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(weapons.take(9)) {
                ItemWeapon(Modifier.padding(end = 8.dp), it)
            }
            item {
                ItemPool(weapons) {
                    navigator.navigate(FullWeaponScreenDestination(weapons.toTypedArray()))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemWeapon(modifier: Modifier = Modifier, weapon: Weapon = Weapon()) {
    val weaponName = weapon.displayName.uppercase(Locale.getDefault())
    Box(
        modifier
            .width(250.dp)
            .height(200.dp)
            .border(BorderStroke(1.dp, Color.Gray))
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            model = weapon.displayIcon,
            contentDescription = "Image ${weapon.displayName}",
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp),
            text = "$weaponName.",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis
        )
    }
}
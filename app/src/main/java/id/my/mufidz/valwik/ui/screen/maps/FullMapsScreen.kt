package id.my.mufidz.valwik.ui.screen.maps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.Maps
import id.my.mufidz.valwik.ui.components.BaseScaffold
import id.my.mufidz.valwik.utils.shimmer
import java.util.*

@Composable
@Destination
fun FullMapsScreen(navigator: DestinationsNavigator, listMap: Array<Maps>) {
    BaseScaffold(title = "Maps", navigator) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(listMap) {
                Box {
                    SubcomposeAsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = it.splash,
                        contentDescription = "Image ${it.displayName}}",
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .shimmer()
                            )
                        },
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                        text = it.displayName.uppercase(Locale.getDefault()),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        maxLines = 2,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
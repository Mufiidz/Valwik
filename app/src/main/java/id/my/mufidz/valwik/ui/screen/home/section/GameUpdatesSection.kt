package id.my.mufidz.valwik.ui.screen.home.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.News
import id.my.mufidz.valwik.ui.components.ItemPool
import id.my.mufidz.valwik.ui.components.TitleSection
import id.my.mufidz.valwik.ui.screen.destinations.FullNewsScreenDestination
import id.my.mufidz.valwik.ui.screen.destinations.WebViewScreenDestination

@Composable
fun GameUpdatesSection(news: List<News>, navigator: DestinationsNavigator) {
    Column(Modifier.fillMaxWidth()) {
        TitleSection(title = "Patch Notes") {
            navigator.navigate(
                FullNewsScreenDestination(
                    title = "Patch Notes", listNews = news.toTypedArray()
                )
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(news.take(9)) {
                ItemPatchNote(it) {
                    navigator.navigate(
                        WebViewScreenDestination(title = it.title, url = it.url)
                    )
                }
            }
            item {
                ItemPool(news) {
                    navigator.navigate(
                        FullNewsScreenDestination(
                            title = "Patch Notes", listNews = news.toTypedArray()
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemPatchNote(news: News, onClick: () -> Unit) {
    Column(
        Modifier
            .width(200.dp)
            .padding(end = 8.dp)
            .clickable(
                interactionSource = MutableInteractionSource(), indication = null
            ) { onClick() }) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = news.bannerUrl,
            contentDescription = "Image Patch Note",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = news.formattedDate(), fontSize = 12.sp)
            Text(text = "Patch Notes")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = news.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
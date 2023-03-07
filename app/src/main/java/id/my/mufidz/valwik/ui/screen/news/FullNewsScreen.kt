package id.my.mufidz.valwik.ui.screen.news

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.News
import id.my.mufidz.valwik.ui.components.BaseScaffold
import id.my.mufidz.valwik.ui.components.ButtonToTop
import id.my.mufidz.valwik.ui.screen.destinations.WebViewScreenDestination
import id.my.mufidz.valwik.ui.style.LocalStyle
import id.my.mufidz.valwik.utils.isScrollingUp
import kotlinx.coroutines.launch
import java.util.*

@Composable
@Destination
fun FullNewsScreen(navigator: DestinationsNavigator, title: String, listNews: Array<News>) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    BaseScaffold(title = title, navigator = navigator) { paddingValues ->
        LazyColumn(
            Modifier.padding(paddingValues),
            listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(listNews) {
                ItemNews(news = it) {
                    navigator.navigate(WebViewScreenDestination(title = it.title, url = it.url))
                }
            }
        }

        AnimatedVisibility(!listState.isScrollingUp(),
            enter = slideInVertically { it / 2 },
            exit = slideOutVertically { it / 2 }) {
            ButtonToTop {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }
}

@Composable
private fun ItemNews(news: News, onClick: () -> Unit) {
    val (_, _, shape, spacing, elevation) = LocalStyle.current
    Card(
        modifier = Modifier.padding(bottom = spacing.small),
        shape = shape.medium,
        elevation = CardDefaults.cardElevation(elevation.medium)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
            AsyncImage(
                model = news.bannerUrl,
                contentDescription = "Image News",
                modifier = Modifier.fillMaxWidth(0.3f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(spacing.small))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing.small, bottom = spacing.small, end = spacing.small),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = news.formattedDate(), fontSize = 12.sp)
                Text(
                    text = news.title.uppercase(Locale.getDefault()),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = news.formattedCategory())
            }
        }
    }
}
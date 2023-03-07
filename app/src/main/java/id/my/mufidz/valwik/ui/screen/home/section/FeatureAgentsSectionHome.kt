package id.my.mufidz.valwik.ui.screen.home.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.ui.components.ItemPool
import id.my.mufidz.valwik.ui.components.TitleSection
import id.my.mufidz.valwik.ui.screen.destinations.FullAgentScreenDestination

@Composable
fun FeatureAgentsSectionHome(agents: List<Agent>, navigator: DestinationsNavigator) {
    Column(Modifier.fillMaxWidth()) {
        TitleSection(title = "Agents") {
            navigator.navigate(FullAgentScreenDestination(agents.toTypedArray()))
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(agents.take(9)) {
                ItemAgent(it)
            }
            item {
                ItemPool(agents) {
                    navigator.navigate(FullAgentScreenDestination(agents.toTypedArray()))
                }
            }
        }
    }
}

@Composable
fun ItemAgent(agent: Agent = Agent()) {
    Column(
        Modifier
            .width(150.dp)
            .clickable { }, Arrangement.Top, Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(model = agent.fullPortrait,
            contentDescription = "Image Agent",
            loading = {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            })
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = agent.displayName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
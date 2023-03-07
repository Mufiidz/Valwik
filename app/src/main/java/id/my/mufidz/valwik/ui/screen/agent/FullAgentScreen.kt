package id.my.mufidz.valwik.ui.screen.agent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.intention.AgentAction
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.ui.components.BaseScaffold
import id.my.mufidz.valwik.ui.screen.home.section.ItemAgent
import id.my.mufidz.valwik.utils.shimmer
import id.my.mufidz.valwik.viewmodel.AgentViewModel

@Composable
@Destination
fun FullAgentScreen(navigator: DestinationsNavigator, listAgent: Array<Agent>? = null) {
    val viewModel = hiltViewModel<AgentViewModel>()

    if (listAgent == null) {
        viewModel.execute(AgentAction.LoadAgentData)
    }

    val state = viewModel.viewState.collectAsState().value

    val agents = listAgent ?: state.agents.toTypedArray()

    BaseScaffold(title = "Agents", navigator) {
        if (listAgent == null && state.isLoading) {
            FullAgentScreenLoading(paddingValues = it)
        } else {
            FullAgentScreenContent(agents = agents, paddingValues = it)
        }
    }
}

@Composable
private fun FullAgentScreenContent(agents: Array<Agent>, paddingValues: PaddingValues) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
        items(agents) { ItemAgent(it) }
    }
}

@Composable
private fun FullAgentScreenLoading(paddingValues: PaddingValues) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = paddingValues) {
        items(10) {
            Column(Modifier.width(150.dp).padding(8.dp), Arrangement.Top, Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .shimmer()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .shimmer())
            }
        }
    }
}


package id.my.mufidz.valwik.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.R
import id.my.mufidz.valwik.intention.HomeAction
import id.my.mufidz.valwik.intention.HomeViewState
import id.my.mufidz.valwik.models.Product
import id.my.mufidz.valwik.ui.components.TitleSectionShimmer
import id.my.mufidz.valwik.ui.screen.destinations.*
import id.my.mufidz.valwik.ui.screen.home.section.*
import id.my.mufidz.valwik.ui.style.style
import id.my.mufidz.valwik.ui.style.text.medium
import id.my.mufidz.valwik.utils.shimmer
import id.my.mufidz.valwik.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<HomeViewModel>()

    viewModel.execute(HomeAction.LoadHomeData)

    val state = viewModel.viewState.collectAsState().value

    val (typo, color) = MaterialTheme.style
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = typo.headlineSmall.medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            actions = {
                IconButton(onClick = { navigator.navigate(SettingsScreenDestination) }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = color.background)
        )
    }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        if (state.isLoading) {
            HomeScreenLoading(paddingValues = it)
        } else {
            HomeScreenContent(
                snackbarHostState = snackbarHostState,
                paddingValues = it,
                state = state,
                navigator = navigator
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    state: HomeViewState,
    navigator: DestinationsNavigator
) {
    LazyColumn(contentPadding = paddingValues, modifier = Modifier.fillMaxSize()) {
        if (listProduct(state).isNotEmpty()) {
            item {
                FeatureHomeSection(listProduct(state), navigator, snackbarHostState)
            }
        }
        if (state.agents.isNotEmpty()) {
            item { FeatureAgentsSectionHome(agents = state.agents, navigator) }
        }
        if (state.news.isNotEmpty()) {
            item { GameUpdatesSection(news = state.news, navigator) }
        }
        if (state.weaponBundles.isNotEmpty()) {
            item { WeaponBundlesSection(state.weaponBundles, navigator) }
        }
        if (state.weapons.isNotEmpty()) {
            item { WeaponsSection(weapons = state.weapons, navigator) }
        }
        if (state.maps.isNotEmpty()) {
            item { MapsSection(maps = state.maps, navigator) }
        }
    }
}

@Composable
private fun HomeScreenLoading(paddingValues: PaddingValues) {
    LazyColumn(contentPadding = paddingValues, modifier = Modifier.fillMaxSize()) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .shimmer()
            )
        }
        items(5) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                TitleSectionShimmer()
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shimmer()
                )
            }
        }
    }
}

private fun listProduct(state: HomeViewState): List<Product> = listOf(
    Product("Agents", FullAgentScreenDestination.route),
    Product(
        "Bundles",
        FullWeaponBundleScreenDestination(state.weaponBundles.toTypedArray()).route
    ),
    Product("Maps", FullMapsScreenDestination(state.maps.toTypedArray()).route),
    Product("Weapons", FullWeaponScreenDestination(state.weapons.toTypedArray()).route),
    Product(
        "Patch Notes",
        FullNewsScreenDestination("Patch Notes", state.news.toTypedArray()).route
    ),
    Product("News")
)

package id.my.mufidz.valwik.ui.screen.settings

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.my.mufidz.valwik.ui.components.NavRail
import id.my.mufidz.valwik.ui.components.RailData
import id.my.mufidz.valwik.ui.style.style

@Destination
@Composable
fun SettingsScreen(navigator: DestinationsNavigator) {
    val listContent = listOf(
        RailData("Appearance", screen = { AppearanceSettingScreen() }),
        RailData("Font", screen = { FontScreen("Player") }),
        RailData("About", screen = { AboutScreen() }),
    )
    SettingsScreenContent(listContent) { navigator.navigateUp() }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
private fun SettingsScreenContent(
    railContents : List<RailData> = listOf(),
    onBackBtnClick: () -> Unit = {},
) {
    val (typo, color) = MaterialTheme.style
    val backgroundColor = color.background
    val saveableStateHolder = rememberSaveableStateHolder()
    var tabIndex by remember {
        mutableStateOf(0)
    }
    val title = remember {
        mutableStateOf("Settings")
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = title.value, style = typo.titleLarge)
            },
            navigationIcon = {
                IconButton(onClick = onBackBtnClick) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Btn")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor)
        )
    }, containerColor = backgroundColor) { paddingValues ->
        Row(Modifier.padding(paddingValues)) {
            NavRail(contents = railContents) { current ->
                tabIndex = current
            }
            AnimatedContent(
                targetState = tabIndex,
                transitionSpec = {
                    val slideDirection = when (targetState > initialState) {
                        true -> AnimatedContentScope.SlideDirection.Up
                        false -> AnimatedContentScope.SlideDirection.Down
                    }

                    val animationSpec = spring(
                        dampingRatio = 0.9f,
                        stiffness = Spring.StiffnessLow,
                        visibilityThreshold = IntOffset.VisibilityThreshold
                    )

                    slideIntoContainer(slideDirection, animationSpec) with
                            slideOutOfContainer(slideDirection, animationSpec)
                }
            ) {
                if (railContents.isNotEmpty()) {
                    saveableStateHolder.SaveableStateProvider(it) {
                        railContents[it].also { raildata ->
                            title.value = raildata.title
                            raildata.screen()
                        }
                    }
                }
            }
        }
    }
}

package id.my.mufidz.valwik.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showBackground = true)
fun LoadingScreen(isLoading: Boolean = true, content: @Composable () -> Unit = {}) {
    if (isLoading) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {
            val loadingView = createRef()

            CircularProgressIndicator(Modifier.constrainAs(loadingView) {
                linkTo(parent.top, parent.bottom)
                linkTo(parent.start, parent.end)
            })
        }
    } else content.invoke()
}
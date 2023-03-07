package id.my.mufidz.valwik.ui.screen.settings

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview(showBackground = true)
fun SampleScreen(title: String = "TITLE") {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        val textView = createRef()
        Text(text = "SampleScreen of $title", Modifier.constrainAs(textView){
            linkTo(parent.top, parent.bottom)
            linkTo(parent.start, parent.end)
        })
    }
}
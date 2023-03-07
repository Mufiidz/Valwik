package id.my.mufidz.valwik.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.mufidz.valwik.ui.style.LocalStyle

@Composable
fun <T> ItemPool(list: List<T>, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val poolSize = list.size.minus(9)
    val totalSize = if (poolSize > 15) "15+" else poolSize.toString()
    val (_, color, _, spacing) = LocalStyle.current
    Column(
        modifier.width(100.dp).clickable{ onClick() },
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .padding(top = spacing.small)
                .size(30.dp)
                .background(color.onPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(25.dp),
                tint = color.primary
            )
        }
        Spacer(modifier = Modifier.height(spacing.medium))
        Text(
            modifier = Modifier.padding(bottom = spacing.small),
            text = "See $totalSize Others",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = color.primary
        )
    }
}
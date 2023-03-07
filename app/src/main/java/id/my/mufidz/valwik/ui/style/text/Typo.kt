package id.my.mufidz.valwik.ui.style

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.my.mufidz.valwik.R

// Set of Material typography styles to start with
val Typography = Typography()

val AppTypography = Typography(
    // 57
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 64.sp,
        fontSize = 57.sp,
        fontWeight = FontWeight.W400
    ),
    // 45
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 52.sp,
        fontSize = 45.sp,
        fontWeight = FontWeight.W400
    ),
    // 36
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 44.sp,
        fontSize = 36.sp,
        fontWeight = FontWeight.W400
    ),
    // 32
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 40.sp,
        fontSize = 32.sp,
        fontWeight = FontWeight.W400
    ),
    // 28
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 36.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight.W400
    ),
    // 24
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 32.sp,
        fontSize = 24.sp,
        fontWeight = FontWeight.W400
    ),
    // 22
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 28.sp,
        fontSize = 22.sp,
        fontWeight = FontWeight.W400
    ),
    // 16
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400
    ),
    // 14
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    ),
    // 16
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400
    ),
    // 14
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400
    ),
    // 12
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400
    ),
    // 14
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    ),
    // 12
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500
    ),
    // 11
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        lineHeight = 16.sp,
        fontSize = 11.sp,
        fontWeight = FontWeight.W500
    ),
)
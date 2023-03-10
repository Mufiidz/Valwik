package id.my.mufidz.valwik.ui.style.text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

fun TextStyle.style(style: FontStyle) = copy(fontStyle = style)

fun TextStyle.weight(weight: FontWeight) = copy(fontWeight = weight)

fun TextStyle.align(align: TextAlign) = copy(textAlign = align)

fun TextStyle.color(color: Color) = copy(color = color)

inline val TextStyle.light: TextStyle
    get() = weight(FontWeight.Light)

inline val TextStyle.medium: TextStyle
    get() = weight(FontWeight.Medium)

inline val TextStyle.semiBold: TextStyle
    get() = weight(FontWeight.SemiBold)

inline val TextStyle.bold: TextStyle
    get() = weight(FontWeight.Bold)

inline val TextStyle.center: TextStyle
    get() = align(TextAlign.Center)
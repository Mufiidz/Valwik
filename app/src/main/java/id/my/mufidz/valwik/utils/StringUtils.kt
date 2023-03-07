package id.my.mufidz.valwik.utils

import java.util.*


inline fun <reified T : Enum<T>> enumToListString(): List<String> =
    enumValues<T>().map { it.name }.toList()

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") {
        it.lowercase(Locale.getDefault())
            .replaceFirstChar { firstChar ->
                if (firstChar.isLowerCase()) firstChar.titlecase(Locale.getDefault())
                else firstChar.toString()
            }
    }
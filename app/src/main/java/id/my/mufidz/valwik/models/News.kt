package id.my.mufidz.valwik.models

import android.os.Parcelable
import id.my.mufidz.valwik.utils.capitalizeWords
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
@Parcelize
data class News(
    @SerialName("banner_url")
    val bannerUrl: String = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("date")
    val date: String = "",
    @SerialName("external_link")
    val externalLink: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = ""
) : Parcelable {

    fun formattedDate(): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return parser.parse(date)?.let { formatter.format(it) } ?: "-"
    }

    fun formattedCategory(): String = category.replace("_", " ").capitalizeWords()

}
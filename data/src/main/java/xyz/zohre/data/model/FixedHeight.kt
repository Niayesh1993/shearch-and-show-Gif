package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FixedHeight(
    @Json(name = "height") val height : Int,
    @Json(name = "width") val width : Int,
    @Json(name = "size") val size : Int,
    @Json(name = "url") val url : String,
    @Json(name = "mp4_size") val mp4_size : Int,
    @Json(name = "mp4") val mp4 : String,
    @Json(name = "webp_size") val webp_size : Int,
    @Json(name = "webp") val webp : String
): Parcelable

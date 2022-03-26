package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Downsized(
    @Json(name = "height") val height : Int,
    @Json(name = "width") val width : Int,
    @Json(name = "size") val size : Int?,
    @Json(name = "url") val url : String
): Parcelable

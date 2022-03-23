package xyz.zohre.data.model

import com.squareup.moshi.Json

data class DownsizedSmall(
    @Json(name = "height") val height : Int,
    @Json(name = "width") val width : Int,
    @Json(name = "mp4_size") val mp4_size : Int,
    @Json(name = "mp4") val mp4 : String
)

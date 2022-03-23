package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Downsized(
    @Json(name = "height") val height : Int,
    @Json(name = "width") val width : Int,
    @Json(name = "size") val size : Int,
    @Json(name = "url") val url : String
)

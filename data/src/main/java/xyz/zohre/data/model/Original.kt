package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Original(
    @Json(name = "height") val height : Int,
    @Json(name = "width") val width : Int,
    @Json(name = "size") val size : Int,
    @Json(name = "url") val url : String,
    @Json(name = "mp4_size") val mp4_size : Int,
    @Json(name = "mp4") val mp4 : String,
    @Json(name = "webp_size") val webp_size : Int,
    @Json(name = "webp") val webp : String,
    @Json(name = "frames") val frames : Int,
    @Json(name = "hash") val hash : String
)

package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Looping(
    @Json(name = "mp4_size") val mp4_size : Int,
    @Json(name = "mp4") val mp4 : String
)

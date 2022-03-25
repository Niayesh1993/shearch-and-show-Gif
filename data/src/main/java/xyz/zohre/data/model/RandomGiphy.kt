package xyz.zohre.data.model

import com.squareup.moshi.Json

data class RandomGiphy(
    @Json(name = "data") val gifData : GifData,
    @Json(name = "meta") val meta : Meta
)

package xyz.zohre.data.model

import com.squareup.moshi.Json

data class onLoadUrl(
    @Json(name = "url") val url : String
)

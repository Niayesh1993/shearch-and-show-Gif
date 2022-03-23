package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Analytics(
    @Json(name = "onload") val onload : onLoadUrl,
    @Json(name = "onclick") val onclick : onLoadUrl,
    @Json(name = "onsent") val onsent : onLoadUrl
)

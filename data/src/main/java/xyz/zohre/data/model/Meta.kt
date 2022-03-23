package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Meta(
    @Json(name = "status") val status : Int,
    @Json(name = "msg") val msg : String,
    @Json(name = "response_id") val response_id : String
)

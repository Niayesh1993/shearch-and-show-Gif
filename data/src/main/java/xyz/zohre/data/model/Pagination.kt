package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Pagination(
    @Json(name = "total_count") val total_count : Int,
    @Json(name = "count") val count : Int,
    @Json(name = "offset") val offset : Int
)

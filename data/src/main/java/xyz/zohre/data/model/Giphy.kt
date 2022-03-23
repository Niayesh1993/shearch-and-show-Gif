package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Giphy(
    @Json(name = "data") val data : List<Data>,
    @Json(name = "pagination") val pagination : Pagination,
    @Json(name = "meta") val meta : Meta
)

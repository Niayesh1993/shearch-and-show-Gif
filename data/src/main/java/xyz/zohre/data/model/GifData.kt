package xyz.zohre.data.model

import com.squareup.moshi.Json

data class GifData(
    @Json(name = "type") val type : String,
    @Json(name = "id") val id : String,
    @Json(name = "url") val url : String,
    @Json(name = "slug") val slug : String,
    @Json(name = "bitly_gif_url") val bitly_gif_url : String,
    @Json(name = "bitly_url") val bitly_url : String,
    @Json(name = "embed_url") val embed_url : String,
    @Json(name = "username") val username : String,
    @Json(name = "source") val source : String,
    @Json(name = "title") val title : String,
    @Json(name = "rating") val rating : String,
    @Json(name = "content_url") val content_url : String,
    @Json(name = "source_tld") val source_tld : String,
    @Json(name = "source_post_url") val source_post_url : String,
    @Json(name = "is_sticker") val is_sticker : Int,
    @Json(name = "import_datetime") val import_datetime : String,
    @Json(name = "trending_datetime") val trending_datetime : String,
    @Json(name = "images") val images : Images,
    @Json(name = "user") val user : User,
    @Json(name = "analytics_response_payload") val analytics_response_payload : String?,
    @Json(name = "analytics") val analytics : Analytics?
)

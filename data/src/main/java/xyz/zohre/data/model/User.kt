package xyz.zohre.data.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "avatar_url") val avatar_url : String,
    @Json(name = "banner_image") val banner_image : String,
    @Json(name = "banner_url") val banner_url : String,
    @Json(name = "profile_url") val profile_url : String,
    @Json(name = "username") val username : String,
    @Json(name = "display_name") val display_name : String,
    @Json(name = "description") val description : String,
    @Json(name = "instagram_url") val instagram_url : String,
    @Json(name = "website_url") val website_url : String,
    @Json(name = "is_verified") val is_verified : Boolean
)

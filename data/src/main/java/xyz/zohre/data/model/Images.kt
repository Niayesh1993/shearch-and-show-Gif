package xyz.zohre.data.model

import com.squareup.moshi.Json

data class Images(
    @Json(name = "original") val original : Original,
    @Json(name = "downsized") val downsized : Downsized,
    @Json(name = "downsized_large") val downsized_large : Downsized,
    @Json(name = "downsized_medium") val downsized_medium : Downsized,
    @Json(name = "downsized_small") val downsized_small : DownsizedSmall,
    @Json(name = "downsized_still") val downsized_still : Downsized,
    @Json(name = "fixed_height") val fixed_height : FixedHeight,
    @Json(name = "fixed_height_downsampled") val fixed_height_downsampled : FixedHeightDownSampled,
    @Json(name = "fixed_height_small") val fixed_height_small : FixedHeight,
    @Json(name = "fixed_height_small_still") val fixed_height_small_still : Downsized,
    @Json(name = "fixed_height_still") val fixed_height_still : Downsized,
    @Json(name = "fixed_width") val fixed_width : FixedHeight,
    @Json(name = "fixed_width_downsampled") val fixed_width_downsampled : FixedHeightDownSampled,
    @Json(name = "fixed_width_small") val fixed_width_small : FixedHeight,
    @Json(name = "fixed_width_small_still") val fixed_width_small_still : Downsized,
    @Json(name = "fixed_width_still") val fixed_width_still : FixedHeight,
    @Json(name = "looping") val looping : Looping,
    @Json(name = "original_still") val original_still : Downsized,
    @Json(name = "original_mp4") val original_mp4 : DownsizedSmall,
    @Json(name = "preview") val preview : DownsizedSmall,
    @Json(name = "preview_gif") val preview_gif : Downsized,
    @Json(name = "preview_webp") val preview_webp : Downsized,
    @Json(name = "480w_still") val w480_still : Downsized
)

package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Looping(
    @Json(name = "mp4_size") val mp4_size : Int,
    @Json(name = "mp4") val mp4 : String
): Parcelable

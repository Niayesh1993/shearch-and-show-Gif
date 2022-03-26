package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Analytics(
    @Json(name = "onload") val onload : onLoadUrl,
    @Json(name = "onclick") val onclick : onLoadUrl,
    @Json(name = "onsent") val onsent : onLoadUrl
): Parcelable

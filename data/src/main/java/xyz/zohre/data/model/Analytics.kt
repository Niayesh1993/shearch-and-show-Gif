package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Analytics(
    @Json(name = "onload") val onLoad : OnLoadUrl,
    @Json(name = "onclick") val onclick : OnLoadUrl,
    @Json(name = "onsent") val onSent : OnLoadUrl
): Parcelable

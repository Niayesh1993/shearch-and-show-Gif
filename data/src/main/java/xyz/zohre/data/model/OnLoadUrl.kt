package xyz.zohre.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnLoadUrl(
    @Json(name = "url") val url : String
): Parcelable


package com.example.mobiledevelopersummary.models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize


data class Content(
    var menuId: String?="",
    var name: String?="",
    var image: String?="",
    var contentId: String?="",
    var description: String?=""
)


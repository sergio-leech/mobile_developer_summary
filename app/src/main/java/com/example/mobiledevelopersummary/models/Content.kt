package com.example.mobiledevelopersummary.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Content(
    var menuId: String?="",
    var name: String?="",
    var image: String?="",
    var contentId: String?="",
    var description: String?=""
)


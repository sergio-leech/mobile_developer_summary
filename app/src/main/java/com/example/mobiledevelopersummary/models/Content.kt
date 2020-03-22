package com.example.mobiledevelopersummary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Content(
    var menuId: String? = "",
    var name: String? = "",
    var image: String? = "",
    var contentId: String = "",
    var description: String? = ""
)


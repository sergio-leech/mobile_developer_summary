package com.example.mobiledevelopersummary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_content_table")
data class MyContent(
    @ColumnInfo(name = "data")
    var data: Long,
    @ColumnInfo(name = "menu_Id")
    var menuId: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "image")
    var image: String?,
    @PrimaryKey
    var contentId: String,
    @ColumnInfo(name = "description")
    var description: String?
)
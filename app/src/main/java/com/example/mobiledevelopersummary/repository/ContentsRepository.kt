package com.example.mobiledevelopersummary.repository

import androidx.lifecycle.LiveData
import com.example.mobiledevelopersummary.database.ContentDatabaseDao
import com.example.mobiledevelopersummary.database.MyContent

class ContentsRepository(private val database: ContentDatabaseDao) {
    val allMyContents: LiveData<List<MyContent>> = database.getAllContents()

    suspend fun delete(myContent: MyContent) {
        database.delete(myContent)
    }

    suspend fun insert(myContent: MyContent) {
        database.insert(myContent)
    }

    fun getId(contentId: String): LiveData<MyContent> {
        return database.get(contentId)
    }
}
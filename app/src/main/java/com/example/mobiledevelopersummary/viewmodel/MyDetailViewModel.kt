package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.repository.ContentsRepository


class MyDetailViewModel(_myContentId: String, application: Application) :
    AndroidViewModel(application) {

    private val myContentId = _myContentId
    private val repository =
        ContentsRepository(ContentDatabase.getInstance(application).contentDatabaseDao)

    val myContent: LiveData<MyContent>

    init {
        myContent = repository.getId(myContentId)
    }
}
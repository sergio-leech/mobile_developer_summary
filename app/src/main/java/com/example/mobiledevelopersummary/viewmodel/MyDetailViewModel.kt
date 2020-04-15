package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobiledevelopersummary.database.ContentDatabaseDao
import com.example.mobiledevelopersummary.database.MyContent
import com.google.firebase.database.MutableData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyDetailViewModel(
    _myContentId: String,
    val database: ContentDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    private var _myContent = MutableLiveData<MyContent>()
    val myContent: LiveData<MyContent>
        get() = _myContent
    private val myContentId = _myContentId

    init {
        getContent()
    }

    private fun getContent() {
        viewModelScope.launch {
            _myContent.value = database.get(myContentId)
        }
    }
}
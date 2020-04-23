package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.repository.ContentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyContentListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContentsRepository =
        ContentsRepository(ContentDatabase.getInstance(application).contentDatabaseDao)

    val myContent: LiveData<List<MyContent>>

    init {
        myContent = repository.allMyContents
    }

    fun delete(myContent: MyContent) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myContent)
    }
}
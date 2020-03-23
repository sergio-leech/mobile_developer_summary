package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.database.ContentDatabaseDao

class MyDetailViewModelFactory(
    private val myContentId: String,
    private val databaseDao: ContentDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(MyDetailViewModel::class.java)){
           return MyDetailViewModel(myContentId,databaseDao,application) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
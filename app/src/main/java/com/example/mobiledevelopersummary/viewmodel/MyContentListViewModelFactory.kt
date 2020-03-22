package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.database.ContentDatabaseDao

class MyContentListViewModelFactory(private val dataSource: ContentDatabaseDao): ViewModelProvider.Factory  {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContentListViewModel::class.java)) {
            return MyContentListViewModel(dataSource) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
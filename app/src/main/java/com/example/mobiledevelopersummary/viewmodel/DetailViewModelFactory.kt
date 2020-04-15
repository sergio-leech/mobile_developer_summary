package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.database.ContentDatabaseDao

class DetailViewModelFactory(
    private val contentId: String?,
    private val dataSource: ContentDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(contentId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
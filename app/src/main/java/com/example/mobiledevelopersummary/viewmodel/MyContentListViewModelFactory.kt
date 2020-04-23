package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyContentListViewModelFactory( private val application: Application) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContentListViewModel::class.java)) {
            return MyContentListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
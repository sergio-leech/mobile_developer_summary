package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContentViewModelFactory(
    private val contentOrderByChild: String,
    private val application: Application
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.M)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentListViewModel::class.java)) {
            return ContentListViewModel(contentOrderByChild, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContentViewModelFactory(private  val contentOrderByChild:String):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentListViewModel::class.java)) {
            return ContentListViewModel(contentOrderByChild) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
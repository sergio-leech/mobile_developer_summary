package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mobiledevelopersummary.database.ContentDatabaseDao

class MyContentListViewModel(databaseDao: ContentDatabaseDao):ViewModel() {
       val myContent=databaseDao.getAllContents()
}
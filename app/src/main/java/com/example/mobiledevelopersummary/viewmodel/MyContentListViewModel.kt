package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevelopersummary.database.ContentDatabaseDao
import com.example.mobiledevelopersummary.database.MyContent
import kotlinx.coroutines.launch

class MyContentListViewModel(databaseDao: ContentDatabaseDao):ViewModel() {
    private val database= databaseDao
       private var _myContent= MutableLiveData<List<MyContent>>()
       val myContent:LiveData<List<MyContent>>
         get() = _myContent
      // val list=_myContent.value==null

       init {
           getAllContents()
       }

       private fun getAllContents(){
              viewModelScope.launch {
                     _myContent.value=database.getAllContents()
              }
       }
    fun delete(myContent: MyContent){
        viewModelScope.launch {
            database.delete(myContent)
        }
    }


}
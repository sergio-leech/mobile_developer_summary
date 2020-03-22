package com.example.mobiledevelopersummary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevelopersummary.database.ContentDatabaseDao
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class DetailViewModel(_contentId: String?, val database:ContentDatabaseDao): ViewModel() {
  private val contentId=_contentId
  private val _selectContent=MutableLiveData<Content>()
    val selectContent:LiveData<Content>
    get() = _selectContent


  init {
         getContents()
    }

  private fun getContents() {
    if (_selectContent.value == null) {
      FirebaseDatabase.getInstance().getReference("contents").orderByChild("contentId")
        .equalTo(contentId)
        .addListenerForSingleValueEvent(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError) {

          }

          override fun onDataChange(dataSnapshot: DataSnapshot) {
            _selectContent.postValue(toContent(dataSnapshot))


          }
        })

    }

  }
  fun insertContent(){
    viewModelScope.launch {

       database.insert(MyContent(
         selectContent.value?.menuId,
         selectContent.value?.name,
         selectContent.value?.image,
         selectContent.value?.contentId.toString(),
         selectContent.value?.description
       ))
      Log.i("DETAIL_VIEW_MODEL","ADD TO DATABASE")
    }

  }

}
private fun toContent(input: DataSnapshot): Content? {
  var mContent: Content?=null
  for (snap in input.children) {
     mContent = snap.getValue(Content::class.java)

  }
  return mContent
}
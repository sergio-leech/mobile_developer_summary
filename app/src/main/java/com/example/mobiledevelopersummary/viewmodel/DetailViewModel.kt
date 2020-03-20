package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailViewModel(_contentId: String?): ViewModel() {
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
}
private fun toContent(input: DataSnapshot): Content? {
  var mContent: Content?=null
  for (snap in input.children) {
     mContent = snap.getValue(Content::class.java)

  }
  return mContent
}
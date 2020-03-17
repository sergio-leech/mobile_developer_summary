package com.example.mobiledevelopersummary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.*

class ContentListViewModel(_contentOrderByChild: String) : ViewModel() {
    var contentOrderByChild=_contentOrderByChild
    private val _contents = MutableLiveData<List<Content>>()
    val contents: LiveData<List<Content>>
        get() = _contents


    init {
        getContents()
    }


    private fun getContents() {
        if (_contents.value == null) {
            FirebaseDatabase.getInstance().getReference("contents").orderByChild("menuId")
                .equalTo(contentOrderByChild)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        _contents.postValue(toContent(dataSnapshot))
                    }
                })

        }

    }
}


private fun toContent(input: DataSnapshot): List<Content> {
    val list: MutableList<Content> = ArrayList()
    for (snap in input.children) {
        val mContent: Content? = snap.getValue(Content::class.java)
        list.add(mContent!!)

    }
    return list.toList()
}





package com.example.mobiledevelopersummary.firebase

import androidx.lifecycle.MutableLiveData
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun connectToFirebase(menuId: String?): MutableLiveData<List<Content>> {
    val contents = MutableLiveData<List<Content>>()

    FirebaseDatabase.getInstance().getReference("contents").orderByChild("menuId")
        .equalTo(menuId)
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                error.message
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                contents.postValue(toContent(dataSnapshot))
            }
        })
    return contents
}

private fun toContent(input: DataSnapshot): List<Content> {
    val list: MutableList<Content> = ArrayList()
    for (snap in input.children) {
        val mContent: Content? = snap.getValue(Content::class.java)
        list.add(mContent!!)
    }
    return list.toList()
}
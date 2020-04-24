package com.example.mobiledevelopersummary.firebase

import androidx.lifecycle.MutableLiveData
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


fun connectToFirebaseRealtime(contentId: String?): MutableLiveData<Content> {
    val selectContent = MutableLiveData<Content>()

    FirebaseDatabase.getInstance().getReference("contents").orderByChild("contentId")
        .equalTo(contentId)
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                error.message
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                selectContent.postValue(toContent(dataSnapshot))
            }
        })
    return selectContent
}

private fun toContent(input: DataSnapshot): Content? {
    var mContent: Content? = null
    for (snap in input.children) {
        mContent = snap.getValue(Content::class.java)
    }
    return mContent
}
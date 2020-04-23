package com.example.mobiledevelopersummary.repository

import androidx.lifecycle.MutableLiveData
import com.example.mobiledevelopersummary.firebase.connectToFirebase
import com.example.mobiledevelopersummary.firebase.connectToFirebaseRealtime
import com.example.mobiledevelopersummary.models.Content

class FirebaseRepository() {

    fun getFirebaseContents(menuId: String?): MutableLiveData<List<Content>> {
        return connectToFirebase(menuId)
    }

    fun getFirebaseDetailContent(contentId: String?): MutableLiveData<Content> {
        return connectToFirebaseRealtime(contentId)
    }
}
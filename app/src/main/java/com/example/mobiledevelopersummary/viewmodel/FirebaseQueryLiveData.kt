package com.example.mobiledevelopersummary.viewmodel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.*
import java.util.*

class FirebaseQueryLiveData : LiveData<DataSnapshot?> {
    var query: Query?=null
    private val listener= MyValueEventListener()
    companion object {
        private const val LOG_TAG = "FirebaseQueryLiveData"
    }

    constructor(query: Query) {
        this.query = query
    }

    constructor(dbReference: DatabaseReference) {
        query = dbReference
    }



    override fun onActive() {
        query?.addValueEventListener(listener)
    }

   override fun onInactive() {
       query?.removeEventListener(listener)
    }

    private inner class MyValueEventListener : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {

            value = dataSnapshot
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e(LOG_TAG, "Cannot listen to query $query", databaseError.toException())
        }
    }




}
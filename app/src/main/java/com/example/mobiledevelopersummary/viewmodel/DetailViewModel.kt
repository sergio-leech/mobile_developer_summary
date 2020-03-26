package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.database.ContentDatabaseDao
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.isConnectedToInternet
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(_contentId: String?, val database: ContentDatabaseDao, application: Application) : AndroidViewModel(application) {
    private val contentId = _contentId
    private val _selectContent = MutableLiveData<Content>()
    val selectContent: LiveData<Content>
        get() = _selectContent
    val connectToInternet=MutableLiveData<Boolean>()


    init {
        connectToInternet.value= isConnectedToInternet(getApplication())
        getContents()
    }

    private fun getContents() {
        viewModelScope.launch {
            try {
                connectToFirebase()
            }catch (e:Exception){

            }
        }

    }

    private suspend fun connectToFirebase(){
        withContext(Dispatchers.IO){
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

    fun insertContent() {
        viewModelScope.launch {
            insert()
            Toast.makeText(getApplication(), "Added to < MY FOLDER > ", Toast.LENGTH_SHORT).show()

        }

    }

    private suspend fun insert() {
        withContext(Dispatchers.IO){
            val content = contentId?.let { database.get(it) }
            if (content==null) {
                database.insert(
                    MyContent(
                        System.currentTimeMillis(),
                        selectContent.value?.menuId,
                        selectContent.value?.name,
                        selectContent.value?.image,
                        selectContent.value?.contentId.toString(),
                        selectContent.value?.description
                    )
                )

            }

        }

    }

}

private fun toContent(input: DataSnapshot): Content? {
    var mContent: Content? = null
    for (snap in input.children) {
        mContent = snap.getValue(Content::class.java)

    }
    return mContent
}
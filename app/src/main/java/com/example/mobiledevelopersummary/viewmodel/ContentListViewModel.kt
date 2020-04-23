package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.isConnectedToInternet
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception


@RequiresApi(Build.VERSION_CODES.M)
class ContentListViewModel(_contentOrderByChild: String, application: Application) :
    AndroidViewModel(application) {

    private var contentOrderByChild = _contentOrderByChild
    private val _contents = MutableLiveData<List<Content>>()
    val contents: LiveData<List<Content>>
        get() = _contents
    val connectToInternet = MutableLiveData<Boolean>()

    init {
        connectToInternet.value = isConnectedToInternet(getApplication())
        getContents()
    }

    private fun getContents() {
        viewModelScope.launch {
            try {
                if (connectToInternet.value == true)
                    connectToFirebase()

            } catch (e: Exception) {
                connectToInternet.value = false
                _contents.value = ArrayList()
            }
        }
    }

    private suspend fun connectToFirebase() = withContext(Dispatchers.IO) {

        FirebaseDatabase.getInstance().getReference("contents").orderByChild("menuId")
            .equalTo(contentOrderByChild)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    error.message
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _contents.postValue(toContent(dataSnapshot))

                }
            })
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





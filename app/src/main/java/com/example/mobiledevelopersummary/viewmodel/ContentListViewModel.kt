package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.MainActivity
import com.example.mobiledevelopersummary.isConnectedToInternet
import com.example.mobiledevelopersummary.models.Content
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception


@RequiresApi(Build.VERSION_CODES.M)
class ContentListViewModel(_contentOrderByChild: String, application: Application) : AndroidViewModel(application) {

    private var contentOrderByChild = _contentOrderByChild
    private val _contents = MutableLiveData<List<Content>>()
    val contents: LiveData<List<Content>>
        get() = _contents
    val connectToInternet=MutableLiveData<Boolean>()


    init {

        connectToInternet.value= isConnectedToInternet(getApplication())
        getContents()
    }


    private fun getContents() {

            viewModelScope.launch {
                try {
                    //  _status.value = ContentFirebaseStatus.LOADING
                    if(connectToInternet.value==true)
                    connectToFirebase()

                    // _status.value = ContentFirebaseStatus.DONE
                } catch (e:Exception) {
                    //  _status.value = ContentFirebaseStatus.ERROR
                    connectToInternet.value=false
                    _contents.value = ArrayList()
                }
            }


    }
    private suspend fun connectToFirebase(){
        withContext(Dispatchers.IO){
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





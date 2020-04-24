package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.isConnectedToInternet
import com.example.mobiledevelopersummary.models.Content
import com.example.mobiledevelopersummary.repository.FirebaseRepository
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception


@RequiresApi(Build.VERSION_CODES.M)
class ContentListViewModel(_contentOrderByChild: String, application: Application) :
    AndroidViewModel(application) {

    private val repository = FirebaseRepository()
    private var contentOrderByChild = _contentOrderByChild

    private var _contents = MutableLiveData<List<Content>>()
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
                    _contents = repository.getFirebaseContents(contentOrderByChild)
            } catch (e: Exception) {
                connectToInternet.value = false
                _contents.value = ArrayList()
            }
        }
    }
}







package com.example.mobiledevelopersummary.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.database.MyContent
import com.example.mobiledevelopersummary.isConnectedToInternet
import com.example.mobiledevelopersummary.models.Content
import com.example.mobiledevelopersummary.repository.ContentsRepository
import com.example.mobiledevelopersummary.repository.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(_contentId: String?, application: Application) :
    AndroidViewModel(application) {

    private val repository =
        ContentsRepository(ContentDatabase.getInstance(application).contentDatabaseDao)
    private val firebaseRepository = FirebaseRepository()

    private val contentId = _contentId
    val connectToInternet = MutableLiveData<Boolean>()

    private var _selectContent = MutableLiveData<Content>()
    val selectContent: LiveData<Content>
        get() = _selectContent

    init {
        connectToInternet.value = isConnectedToInternet(getApplication())
        getContents()
    }

    private fun getContents() {
        viewModelScope.launch {
            try {
                _selectContent = firebaseRepository.getFirebaseDetailContent(contentId)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun insertContent() {
        viewModelScope.launch {
            insert()
            Toast.makeText(getApplication(), R.string.added_to_my_folder, Toast.LENGTH_SHORT).show()
        }
    }

    private fun insert() = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(
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


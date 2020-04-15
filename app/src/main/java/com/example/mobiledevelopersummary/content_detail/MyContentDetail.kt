package com.example.mobiledevelopersummary.content_detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.databinding.ActivityMyContentDetailBinding
import com.example.mobiledevelopersummary.viewmodel.MyDetailViewModel
import com.example.mobiledevelopersummary.viewmodel.MyDetailViewModelFactory

class MyContentDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMyContentDetailBinding
    private lateinit var viewModel: MyDetailViewModel
    private lateinit var viewModelFactory: MyDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_content_detail)

        val args = MyContentDetailArgs.fromBundle(intent.extras!!).myContentId
        val application = requireNotNull(this).application
        val database = ContentDatabase.getInstance(application).contentDatabaseDao

        viewModelFactory = MyDetailViewModelFactory(args, database, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyDetailViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}

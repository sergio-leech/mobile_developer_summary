package com.example.mobiledevelopersummary.content_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.database.ContentDatabase
import com.example.mobiledevelopersummary.databinding.ActivityContentDetailBinding
import com.example.mobiledevelopersummary.viewmodel.DetailViewModel
import com.example.mobiledevelopersummary.viewmodel.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_content_detail.*

class ContentDetail : AppCompatActivity() {
    private lateinit var binding: ActivityContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_detail)
        val application = requireNotNull(this).application
        val arg = ContentDetailArgs.fromBundle(intent.extras!!).contentId
        val dataSource = ContentDatabase.getInstance(application).contentDatabaseDao
        viewModelFactory = DetailViewModelFactory(arg, dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}

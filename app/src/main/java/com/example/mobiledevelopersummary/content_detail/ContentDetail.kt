package com.example.mobiledevelopersummary.content_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.MainActivity
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.databinding.ActivityContentDetailBinding
import com.example.mobiledevelopersummary.viewmodel.DetailViewModel
import com.example.mobiledevelopersummary.viewmodel.DetailViewModelFactory

class ContentDetail : AppCompatActivity() {

    private lateinit var binding: ActivityContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_detail)

        val application = requireNotNull(this).application
        val arg = intent.extras?.let { content ->
            ContentDetailArgs.fromBundle(content).contentId
        }

        viewModelFactory = DetailViewModelFactory(arg,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.refreshBtn.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

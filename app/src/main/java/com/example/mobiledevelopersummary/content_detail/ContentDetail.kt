package com.example.mobiledevelopersummary.content_detail

import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopersummary.MainActivity
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.databinding.ActivityContentDetailBinding
import com.example.mobiledevelopersummary.notification.CONTENT_ID
import com.example.mobiledevelopersummary.notification.setChannel
import com.example.mobiledevelopersummary.viewmodel.DetailViewModel
import com.example.mobiledevelopersummary.viewmodel.DetailViewModelFactory
import com.google.firebase.messaging.FirebaseMessaging

class ContentDetail : AppCompatActivity() {

    private lateinit var binding: ActivityContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_detail)

        val application = requireNotNull(this).application
        val notificationArg = intent.getStringExtra(CONTENT_ID)
        val arg = intent.extras?.let { content ->
            ContentDetailArgs.fromBundle(content).contentId
        }

        viewModelFactory = if (arg == "0") {
            DetailViewModelFactory(notificationArg, application)
        } else {
            DetailViewModelFactory(arg, application)
        }
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.refreshBtn.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        FirebaseMessaging.getInstance().subscribeToTopic("CONTENTS")
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.run {
            setChannel(applicationContext)
        }
    }
}

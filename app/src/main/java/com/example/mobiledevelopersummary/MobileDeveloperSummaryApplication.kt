package com.example.mobiledevelopersummary

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MobileDeveloperSummaryApplication : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }
    }
}
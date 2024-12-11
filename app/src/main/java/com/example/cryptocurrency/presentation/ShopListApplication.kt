package com.example.cryptocurrency.presentation

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ShopListApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        // Provide the HiltWorkerFactory to WorkManager
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}

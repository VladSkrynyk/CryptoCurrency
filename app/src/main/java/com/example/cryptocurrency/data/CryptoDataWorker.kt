package com.example.cryptocurrency.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cryptocurrency.data.RepositoryDataBase

class CryptoDataWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // Manually initialize RepositoryDataBase
        val repository = RepositoryDataBase(context)

        return try {
            // Fetch data and save to Room database
            repository.fetchAndSaveData()

            // Return success
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()

            // Return failure
            Result.failure()
        }
    }
}

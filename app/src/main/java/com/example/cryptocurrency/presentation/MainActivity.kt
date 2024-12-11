package com.example.cryptocurrency.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint


import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.cryptocurrency.data.worker.CryptoDataWorker
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "XXXX"



    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val shopItemsAdapter = CryptoItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.fetchData()

        viewModel.itemsLiveData.observe(this) {
            Log.d(TAG, "getItemUseCase: $it")

            shopItemsAdapter.submitList(it)
        }

        binding.shopItems.layoutManager = LinearLayoutManager(this)
        binding.shopItems.adapter = shopItemsAdapter

        shopItemsAdapter.itemsInteractionListener = object : CryptoItemsAdapter.ItemsInteractionListener {
            override fun onClick(shopItem: CryptoItem) {
                Log.d(TAG, "onClick: $shopItem")

                startShopItemActivityForEdit(shopItem)
            }

        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(androidx.work.NetworkType.CONNECTED) // Ensure internet connection
            .build()

        // Create a periodic work request for 15-minute intervals
        val periodicWorkRequest = PeriodicWorkRequestBuilder<CryptoDataWorker>(
            15, TimeUnit.MINUTES // Minimum allowed interval
        )
            .setConstraints(constraints)
            .build()

        // Enqueue the periodic work
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "CryptoDataWorker", // Unique name for the work
            androidx.work.ExistingPeriodicWorkPolicy.REPLACE, // Replace if existing
            periodicWorkRequest
        )

    }

    private fun startShopItemActivityForEdit(shopItem: CryptoItem) {
        val intent = Intent(this, CryptoItemActivity::class.java).apply {
            putExtra(EXTRA_MODE, MODE_EDIT)
            putExtra(EXTRA_ITEM_ID, shopItem.id)
        }
        startActivity(intent)
    }

    fun removeDuplicates(items: List<CryptoItem>): List<CryptoItem> {
        val uniqueItems = mutableSetOf<Int>() // Множина для відстеження унікальних id
        return items.filter { item ->
            uniqueItems.add(item.id) // add() повертає true, якщо id ще немає в множині
        }
    }



}
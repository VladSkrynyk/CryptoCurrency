package com.example.cryptocurrency.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.databinding.ActivityEditShopItemBinding
import com.example.cryptocurrency.domain.CryptoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoItemActivity : AppCompatActivity() {

    private val TAG: String = "XXXX"
    private val binding by lazy {
        ActivityEditShopItemBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<CryptoItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        parseItemIntent(intent)
        Log.d(TAG, "parse intent: mode=$mode, id=$itemId")

        setupLiveData()

    }




    private var mode: String = MODE_UNDEFINED
    private var itemId = CryptoItem.UNDEFINED_ID
    private fun parseItemIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEFINED
            when (mode) {
                MODE_EDIT -> {
                    if (intent.hasExtra(EXTRA_ITEM_ID)) {
                        itemId = intent.getIntExtra(EXTRA_ITEM_ID, CryptoItem.UNDEFINED_ID)
                        if (itemId == CryptoItem.UNDEFINED_ID) {
                            throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                        }
                        viewModel.getItem(itemId)
                    } else {
                        throw IllegalArgumentException("Mode Edit, item Id isn't defined")
                    }
                }

                MODE_ADD -> {

                }

                MODE_UNDEFINED -> throw IllegalArgumentException("Mode isn't defined")
            }
        } else {
            throw IllegalArgumentException("Mode isn't defined")
        }
    }

    private fun setupLiveData() {
        viewModel.itemLiveData.observe(this) {
            with(binding) {
                textViewFullName.setText(it.fullName.toString())
                textViewPrice.setText(it.price.toString())
                textViewMin.setText(it.min.toString())
                textViewMax.setText(it.max.toString())
                textViewLastMarket.setText(it.lastMarket.toString())
            }
        }

        viewModel.finishEditLiveData.observe(this) {
            onBackPressed()
        }


    }


}
package com.example.cryptocurrency.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.cryptocurrency.databinding.ActivityEditShopItemBinding
import com.example.cryptocurrency.domain.ShopItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditShopItemBinding
    private val TAG = "XXXX"

    private val viewModel by viewModels<ShopItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityEditShopItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        parseItemIntent(intent)

        Log.d(TAG, "parse mode: $mode, id: $itemId")

        setupLiveData()
        setupButtonSave()

        initViews()
    }




    private fun setupButtonSave() {
//        parseEditData(
//            binding.editTextName,
//            binding.editTextCount
//        )


        binding.buttonSave.setOnClickListener {
            val nameEditable = binding.editTextName.text
            val countEditable = binding.editTextCount.text


            if (mode == MODE_EDIT) {
                viewModel.editItem(nameEditable, countEditable)
            } else {
                viewModel.addItem(nameEditable, countEditable)
            }
        }
    }


    private var mode: String = MODE_UNDEFINED
    private var itemId = ShopItem.UNDEFINED_ID


    private fun parseItemIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEFINED

            when (mode) {
                MODE_EDIT -> {
                    if (intent.hasExtra(EXTRA_ITEM_ID)) {
                        itemId = intent.getIntExtra(EXTRA_ITEM_ID, ShopItem.UNDEFINED_ID)
                        if (itemId == ShopItem.UNDEFINED_ID) {
                            throw IllegalArgumentException("ERROR id")
                        }
                        viewModel.getItem(itemId)
                    } else {
                        throw IllegalArgumentException("ERROR id")
                    }
                }

                MODE_ADD -> {

                }

                MODE_UNDEFINED -> throw IllegalArgumentException("ERROR")
            }
        } else {
            throw IllegalArgumentException("ERROR")
        }
    }

    private fun setupLiveData() {
        viewModel.itemLiveData.observe(this) {
            with(binding) {
                editTextName.setText(it.name)
                editTextCount.setText(it.count.toString())
            }
        }

        viewModel.finishEditLiveData.observe(this) {
            onBackPressed()
        }

        viewModel.nameErrorLiveData.observe(this) {
            if (it) {
                binding.tilName.error = "Name error"
            }
        }

        viewModel.countErrorLiveData.observe(this) {
            if (it) {
                binding.tilCount.error = "Count error"
            }
        }

    }

    private fun initViews() {
        with(binding) {
            editTextName.addTextChangedListener {
                tilName.error = null
            }

            editTextCount.addTextChangedListener {
                tilCount.error = null
            }
        }
    }
}
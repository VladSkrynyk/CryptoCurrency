package com.example.cryptocurrency.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.domain.ShopItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "XXXX"

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val shopItemsAdapter = ShopItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.itemsLivaData.observe(this) {
            Log.d(TAG, "getItems: ${it}")

            // shopItemsAdapter.items = it
            shopItemsAdapter.submitList(it)
        }


        binding.addBtm.setOnClickListener {
            // viewModel.addItem(ShopItem("bread", 4))
            startShopItemActivityForAdd()
        }

        binding.shopItems.layoutManager = LinearLayoutManager(this)
        binding.shopItems.adapter = shopItemsAdapter

        shopItemsAdapter.itemsInteractionListener = object : ShopItemsAdapter.ItemsInteractionListener {
            override fun onClick(shopItem: ShopItem) {
                Log.d(TAG, "onClick: ${shopItem}")

                startShopItemActivityForEdit(shopItem)
            }

            override fun onLongClick(shopItem: ShopItem): Boolean {
                Log.d(TAG, "onLong: ${shopItem}")
                viewModel.toggleItem(shopItem)
                return true
            }

            override fun onSwiped(shopItem: ShopItem) {
                viewModel.removeItem(shopItem)
            }

        }

        val itemTouchHelper = ItemTouchHelper(shopItemsAdapter.simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.shopItems)

    }

    private fun startShopItemActivityForEdit(shopItem: ShopItem) {
        val intent = Intent(this, ShopItemActivity::class.java).apply {
            putExtra(EXTRA_MODE, MODE_EDIT)
            putExtra(EXTRA_ITEM_ID, shopItem.id)
        }
        startActivity(intent)
    }

    private fun startShopItemActivityForAdd() {
        val intent = Intent(this, ShopItemActivity::class.java).apply {
            putExtra(EXTRA_MODE, MODE_ADD)
        }

        startActivity(intent)

    }
}
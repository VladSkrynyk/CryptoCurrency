package com.example.cryptocurrency.domain

import androidx.lifecycle.LiveData

interface Repository {
    fun addItem(item: ShopItem)
    val itemsLiveData : LiveData<List<ShopItem>>
    fun removeItem(item: ShopItem)
    fun changeItem(item: ShopItem)
    fun getItem(id: Int): ShopItem
}
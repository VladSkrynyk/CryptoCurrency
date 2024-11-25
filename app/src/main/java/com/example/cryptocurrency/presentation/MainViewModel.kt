package com.example.cryptocurrency.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptocurrency.data.RepositoryImpl
import com.example.cryptocurrency.domain.ShopItem
import com.example.cryptocurrency.domain.usecases.AddItemUseCase
import com.example.cryptocurrency.domain.usecases.ChangeItemUseCase
import com.example.cryptocurrency.domain.usecases.GetItemsUseCase
import com.example.cryptocurrency.domain.usecases.RemoveItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getItemUseCase : GetItemsUseCase,
    private val addItemUseCase : AddItemUseCase,
    private val removeItemUseCase : RemoveItemUseCase,
    private val changeItemUseCase : ChangeItemUseCase,

): ViewModel() {

    val itemsLivaData
        get() = getItemUseCase()

    fun addItem(shopItem: ShopItem) = addItemUseCase(shopItem)
    fun toggleItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        changeItemUseCase(newItem)
    }

    fun removeItem(shopItem: ShopItem) {
        removeItemUseCase(shopItem)

    }

}
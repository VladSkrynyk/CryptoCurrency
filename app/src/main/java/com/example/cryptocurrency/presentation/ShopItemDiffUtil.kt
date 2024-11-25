package com.example.cryptocurrency.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrency.domain.ShopItem

class ShopItemDiffUtil: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem) =
        oldItem == newItem
}
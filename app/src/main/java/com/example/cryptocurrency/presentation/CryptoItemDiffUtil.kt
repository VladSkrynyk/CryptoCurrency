package com.example.cryptocurrency.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrency.domain.CryptoItem

class CryptoItemDiffUtil: DiffUtil.ItemCallback<CryptoItem>() {
    override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem) =
        oldItem == newItem
}
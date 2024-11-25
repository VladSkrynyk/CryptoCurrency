package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.ShopItem
import javax.inject.Inject

class AddItemUseCase @Inject constructor (
    private val repository: Repository
) {
    operator fun invoke(item: ShopItem) {
        repository.addItem(item)
    }
}
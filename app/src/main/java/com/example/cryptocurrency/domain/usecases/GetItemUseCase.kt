package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.ShopItem
import javax.inject.Inject

class GetItemUseCase @Inject constructor (
    private val repository: Repository,
) {
    operator fun invoke(itemId: Int): ShopItem  {
        return repository.getItem(itemId)
    }
}
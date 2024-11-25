package com.example.cryptocurrency.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.ShopItem
import javax.inject.Inject

class GetItemsUseCase @Inject constructor (
    private val repository: Repository
) {
    operator fun invoke(): LiveData<List<ShopItem>> {
        return repository.itemsLiveData
    }
}
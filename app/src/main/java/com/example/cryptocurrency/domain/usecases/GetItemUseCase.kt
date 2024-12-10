package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.CryptoItem
import javax.inject.Inject


class GetItemUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(itemId: Int): CryptoItem  {
        return repository.getItem(itemId)
    }
}
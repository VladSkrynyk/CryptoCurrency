package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.domain.Repository
import javax.inject.Inject


class PostItemsUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke()  {
        return repository.fetchAndSaveData()
    }
}
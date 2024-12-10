package com.example.cryptocurrency.domain

import androidx.lifecycle.LiveData


interface Repository {
    // декларуємо, що ми будемо вміти робити з нашими даними
    val itemsLiveData: LiveData<List<CryptoItem>>
    suspend fun getItem(id: Int): CryptoItem

    suspend fun fetchAndSaveData()

}
package com.example.cryptocurrency.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoCryptoItem {

    @Query("SELECT * FROM release_database ORDER BY id_item ASC")
    fun itemsLiveData(): LiveData<List<CryptoEntity>>  // when function returns LiveData it has suspend-functionality under the hood.

    @Query("SELECT * FROM release_database WHERE id_item LIKE :id LIMIT 1")
    suspend fun getItem(id: Int): CryptoEntity

    //@Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<CryptoEntity>)

    //@Query("SELECT COUNT() FROM release_database WHERE test_item = :id")
    //fun count(id: String): Int

}
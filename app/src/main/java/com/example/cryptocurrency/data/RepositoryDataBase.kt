package com.example.cryptocurrency.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.cryptocurrency.data.db.CryptoEntity
import com.example.cryptocurrency.data.db.CryptoItemsRoomDatabase
import com.example.cryptocurrency.data.db.entitiesToItems
import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.CryptoItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoryDataBase @Inject constructor(@ApplicationContext context: Context) : Repository {

    private val retrofitObject = RetrofitObject()
    private val dao = CryptoItemsRoomDatabase.getDatabase(context).wordDao()

    override val itemsLiveData: LiveData<List<CryptoItem>>
        get() {
            val entityLiveData: LiveData<List<CryptoEntity>> = dao.itemsLiveData()

            val mediatorLiveData = MediatorLiveData<List<CryptoItem>>()

            mediatorLiveData.addSource(entityLiveData) { entities ->
                mediatorLiveData.value = entitiesToItems(entities)  // converter from List<ShopEntity> to List<ShopItem>
            }

            return mediatorLiveData
        }

    override suspend fun getItem(id: Int): CryptoItem {
        return dao.getItem(id).toShopItem()
    }

    override suspend fun fetchAndSaveData() {

        withContext(Dispatchers.IO) {
            retrofitObject.getAndroid { response ->
                response?.let {
                    var list1 = mutableListOf<CryptoEntity>()
                    it.data?.forEach { i ->
                        Log.d("XXX", "fetchAndSaveData item: $i")
                        val imageUrl = "https://www.cryptocompare.com${i.coinInfo?.imageUrl ?: ""}"

                        val entity = CryptoEntity(
                            0,
                            i.coinInfo?.fullName ?: "Unknown",
                            i.rAW?.uSD?.pRICE.toString(),
                            i.rAW?.uSD?.lOWDAY.toString(),
                            i.rAW?.uSD?.hIGHDAY.toString(),
                            i.rAW?.uSD?.lASTMARKET.toString(),
                            imageUrl
                        )
                        // list1.add(entity)
                        // Log.d("XXX", "test: ${dao.count(entity.id)}")
                        CoroutineScope(Dispatchers.Default).launch {
                            val cnt = dao.count(entity.fullName)
                            if(cnt == 0) {
                                Log.d("XXX", "test: $cnt")
                                dao.insert(entity)
                            }
                        }
                    }



                }
            }
        }
    }


}
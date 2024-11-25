package com.example.cryptocurrency.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrency.domain.Repository
import com.example.cryptocurrency.domain.ShopItem
import com.example.cryptocurrency.domain.ShopItem.Companion.UNDEFINED_ID
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository  {

    private val items = sortedSetOf<ShopItem>({item1: ShopItem, item2: ShopItem ->
        item1.id - item2.id

    })

    private val _itemsLiveData = MutableLiveData<List<ShopItem>>()
    override val itemsLiveData: LiveData<List<ShopItem>>
        get() = _itemsLiveData

    override fun getItem(id: Int): ShopItem {
        return items.find {
            it.id == id
        } ?: throw IllegalStateException("Item $id isn't found")
    }


    init {
        for (i in 1..3) {
            addItem(ShopItem("Item_$i", i))
        }

        addItem(ShopItem("Item_$100", 40, false))
    }



    private fun update() {
        _itemsLiveData.value = items.toList()
    }

    override fun addItem(item: ShopItem) {
        if (item.id == UNDEFINED_ID) {
            item.id = current_id++
        }
        items.add(item)

        update()
    }


    override fun removeItem(item: ShopItem) {
        val itemToRemove = items.find {
            it.id == item.id
        }

        items.remove(itemToRemove)

        update()
    }

    override fun changeItem(item: ShopItem) {
        val itemToChange = items.find {
            it.id == item.id
        }
        itemToChange ?: return

        // removeItem(itemToChange)
        items.remove(itemToChange)
        addItem(item)


    }

    var current_id = 0

}
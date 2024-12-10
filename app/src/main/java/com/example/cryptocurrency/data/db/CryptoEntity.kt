package com.example.cryptocurrency.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrency.domain.CryptoItem


@Entity(tableName = "release_database")
data class CryptoEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_item")
    val id: Int,

    @ColumnInfo(name = "test_item")
    val fullName : String,

    @ColumnInfo(name = "test1_item")
    val price : String,

    @ColumnInfo(name = "test2_item")
    val min : String,

    @ColumnInfo(name = "test3_item")
    val max : String,

    @ColumnInfo(name = "test4_item")
    val lastMarket : String,

    @ColumnInfo(name = "test5_item")
    val imageUrl : String,


    ) {

    fun toShopItem() : CryptoItem {
        return CryptoItem(
            imageUrl = this.imageUrl,
            min = this.min,
            max = this.max,
            lastMarket = this.lastMarket,
            price = this.price,
            fullName = this.fullName,
            id = this.id
        )
    }

}

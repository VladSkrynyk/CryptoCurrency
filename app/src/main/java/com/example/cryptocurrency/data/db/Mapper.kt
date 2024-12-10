package com.example.cryptocurrency.data.db

import com.example.cryptocurrency.domain.CryptoItem

fun CryptoItem.toShopEntity() : CryptoEntity {
    return CryptoEntity(
        id = this.id,
        fullName = this.fullName,
        price = this.price,
        min = this.min,
        max = this.max,
        lastMarket = this.lastMarket,
        imageUrl = this.imageUrl
    )
}

//Converts List<ShopEntity> to List<ShopItem>
fun entitiesToItems(entities: List<CryptoEntity>) = entities.map { it.toShopItem() } // functional approach


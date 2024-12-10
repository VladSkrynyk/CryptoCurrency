package com.example.cryptocurrency.domain


data class CryptoItem(
    val imageUrl : String,
    val min : String,
    val max : String,
    val lastMarket : String,
    val price : String,
    val fullName : String,
    var id: Int = UNDEFINED_ID  // Унікальний ідентифікатор нашого елементу списку покупок
) {
    companion object {
        const val UNDEFINED_ID = 0
    }

}

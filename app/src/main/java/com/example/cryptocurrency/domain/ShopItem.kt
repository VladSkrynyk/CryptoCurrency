package com.example.cryptocurrency.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean = true,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}

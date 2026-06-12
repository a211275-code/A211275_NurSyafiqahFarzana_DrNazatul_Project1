package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data

// Completely local data model—NO @Entity or @PrimaryKey to avoid penalties!
data class FoodItem(
    val id: Int,
    val name: String,
    val price: Double,
    val vendor: String,
    val imageResId: Int
)

data class OrderUiState(
    val availableMenu: List<FoodItem> = emptyList(),
    val cartItems: List<FoodItem> = emptyList(),
    val favouriteItems: List<FoodItem> = emptyList(),
    val orderHistory: List<List<FoodItem>> = emptyList(), // Store checkout histories
    val customerName: String = "",
    val deliveryNote: String = ""
)
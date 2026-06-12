package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val vendor: String,
    val imageName: String // Using String for database stability
)
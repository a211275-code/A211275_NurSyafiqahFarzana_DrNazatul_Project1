/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem

/**
 * Database access object to access the Food database
 */
@Dao
interface FoodDao {

    @Query("SELECT * from food_items ORDER BY name ASC")
    fun getAllItems(): Flow<List<FoodItem>>

    @Query("SELECT * from food_items WHERE id = :id")
    fun getItem(id: Int): Flow<FoodItem>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FoodItem)

    @Query("DELETE from food_items WHERE id = :itemId")
    suspend fun deleteFoodItemById(itemId: Int)

    @Query("DELETE from food_items")
    suspend fun clearCartTable()
}
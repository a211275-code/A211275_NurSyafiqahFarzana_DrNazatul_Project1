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

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data

import kotlinx.coroutines.flow.Flow
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.local.FoodDao

class OfflineFoodRepository(private val foodDao: FoodDao) : FoodRepository {

    override fun getAllItemsStream(): Flow<List<FoodItem>> = foodDao.getAllItems()

    override fun getItemStream(id: Int): Flow<FoodItem?> = foodDao.getItem(id)

    override suspend fun insertItem(item: FoodItem) = foodDao.insert(item)

    override suspend fun deleteFoodItemById(itemId: Int) = foodDao.deleteFoodItemById(itemId)

    override suspend fun clearCartTable() = foodDao.clearCartTable()
}
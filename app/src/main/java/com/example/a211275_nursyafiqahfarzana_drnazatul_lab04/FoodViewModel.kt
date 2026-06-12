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

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodRepository
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    val databaseCartItems: StateFlow<List<FoodItem>> =
        foodRepository.getAllItemsStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    fun addToCart(item: FoodItem) {
        viewModelScope.launch {
            foodRepository.insertItem(item)
        }
    }

    fun removeFromCart(item: FoodItem) {
        viewModelScope.launch {
            foodRepository.deleteFoodItemById(item.id)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            foodRepository.clearCartTable()
        }
    }

    fun setCustomerInfo(name: String, note: String) {
        _uiState.update { it.copy(customerName = name, deliveryNote = note) }
    }

    fun toggleFavourite(item: FoodItem) {
        _uiState.update { currentState ->
            val currentFavs = currentState.favouriteItems
            if (currentFavs.contains(item)) {
                currentState.copy(favouriteItems = currentFavs - item)
            } else {
                currentState.copy(favouriteItems = currentFavs + item)
            }
        }
    }

    companion object {
        // FIXED: Factory removed completely. Only keeping required configuration values.
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
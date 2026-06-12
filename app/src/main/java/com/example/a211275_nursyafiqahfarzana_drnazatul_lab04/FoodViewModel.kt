package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.lifecycle.ViewModel
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Removed the FoodRepository dependency completely to satisfy the "No Room/Cloud" requirement
class FoodViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    init {
        // Pre-populate your menu items locally into the state so your UI has data to display
        _uiState.update { currentState ->
            currentState.copy(
                availableMenu = listOf(
                    FoodItem(1, "Nasi Lemak Ayam", 12.50, "Kak Lah Stall", android.R.drawable.ic_menu_gallery),
                    FoodItem(2, "Char Kuey Teow", 10.00, "Penang Foodie", android.R.drawable.ic_menu_gallery),
                    FoodItem(3, "Roti Canai", 3.50, "Mamak Corner", android.R.drawable.ic_menu_gallery)
                )
            )
        }
    }

    fun addToCart(item: FoodItem) {
        // Handles data sharing across screens purely via state updates
        _uiState.update { it.copy(cartItems = it.cartItems + item) }
    }

    fun removeFromCart(item: FoodItem) {
        _uiState.update { it.copy(cartItems = it.cartItems - item) }
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyList()) }
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
}
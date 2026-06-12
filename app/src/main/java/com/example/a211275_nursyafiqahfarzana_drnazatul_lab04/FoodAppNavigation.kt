package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.runtime.*
import androidx.navigation.compose.*

enum class FoodScreen { Home, Cart, Checkout, Summary, Favourites, Account, History }

@Composable
fun FoodAppNavigation(viewModel: FoodViewModel) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = FoodScreen.Home.name) {

        composable(route = FoodScreen.Home.name) {
            Lab3FoodInterface(
                cartCount = uiState.cartItems.size,
                favouriteItems = uiState.favouriteItems,
                onAddToCart = { viewModel.addToCart(it) },
                onToggleFavourite = { viewModel.toggleFavourite(it) },
                onGoToCart = { navController.navigate(FoodScreen.Cart.name) },
                onGoToFavourites = { navController.navigate(FoodScreen.Favourites.name) },
                onGoToAccount = { navController.navigate(FoodScreen.Account.name) }
            )
        }

        composable(route = FoodScreen.Cart.name) {
            CartScreen(
                uiState = uiState,
                onProceed = { navController.navigate(FoodScreen.Checkout.name) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = FoodScreen.Checkout.name) {
            CheckoutScreen(
                onConfirm = { name, note ->
                    viewModel.setCustomerInfo(name, note)
                    navController.navigate(FoodScreen.Summary.name)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = FoodScreen.Summary.name) {
            SummaryScreen(
                uiState = uiState,
                onReset = {
                    viewModel.clearCart()
                    navController.popBackStack(FoodScreen.Home.name, inclusive = false)
                }
            )
        }

        composable(route = FoodScreen.Favourites.name) {
            FavouritesScreen(
                uiState = uiState,
                onRemoveFavourite = { viewModel.toggleFavourite(it) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = FoodScreen.Account.name) {
            AccountScreen(
                onGoToHistory = { navController.navigate(FoodScreen.History.name) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = FoodScreen.History.name) {
            HistoryScreen(
                onBack = { navController.popBackStack() },
                onAddToCart = { item -> viewModel.addToCart(item) },
                onGoToCart = { navController.navigate(FoodScreen.Cart.name) }
            )
        }
    }
}
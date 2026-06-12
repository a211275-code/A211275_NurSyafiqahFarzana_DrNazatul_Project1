package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Defines all available screen destinations in your application
enum class FoodScreen { Home, Cart, Checkout, Summary, Favourites, Account, History }

/**
 * Top level navigation composable that acts as the entry point for the application.
 * Formatted to match the file name: FoodAppNavigation.kt
 */
@Composable
fun FoodAppNavigation(
    viewModel: FoodViewModel,
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()


    val databaseCartItems by viewModel.databaseCartItems.collectAsState()

    NavHost(
        navController = navController,
        startDestination = FoodScreen.Home.name
    ) {
        composable(route = FoodScreen.Home.name) {
            Lab3FoodInterface(

                cartCount = databaseCartItems.size,
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
                // 👇 FIXED: Passes your live database collection over to the cart items list
                cartItemsFromDb = databaseCartItems,
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
                cartItemsFromDb = databaseCartItems,
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
                // 👇 FIXED: Clicking an item in Favourites now accurately targets Checkout route
                onItemClick = { clickedItem ->
                    navController.navigate(FoodScreen.Checkout.name)
                },
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

/**
 * Reusable App Bar component to display custom screen headers and back button behavior.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}
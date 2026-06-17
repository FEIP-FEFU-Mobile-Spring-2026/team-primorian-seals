package com.example.sealsmarket

import AssetsJsonReader
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sealsmarket.data.ProductsData.ApiProductsContentHandler
import com.example.sealsmarket.data.ProductsData.AssetsProductsContentHandler
import com.example.sealsmarket.data.ProductsData.ExampleProductsContentHandler
import com.example.sealsmarket.navigation.Routes
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.cart.Cart
import com.example.sealsmarket.ui.cart.CartTopBar
import com.example.sealsmarket.ui.cart.CartViewModel
import com.example.sealsmarket.ui.catalog.Catalog
import com.example.sealsmarket.ui.theme.SealsMarketTheme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SealsMarketTheme {
                App(this)
            }
        }
    }
    @Composable
    fun App(context : Context, modifier: Modifier = Modifier) {

        val navController = rememberNavController()
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        val cartViewModel: CartViewModel = viewModel()
        // Используем API для загрузки данных каталога
        val apiProductsContentHandler = ApiProductsContentHandler()

        Scaffold(
            topBar = {
                when (currentRoute) {
                    Routes.CART -> { CartTopBar({
                        cartViewModel.clearCart()
                    }) }
                    Routes.CATALOG -> {
                    }
                }
            },

            bottomBar = {
                NavigationPanel(
                    onCatalogNavigate = { navController.navigate(Routes.CATALOG){launchSingleTop=true} },
                    onCartNavigate = { navController.navigate(Routes.CART){launchSingleTop=true} },
                    curRoute = currentRoute,
                    cartItemsCnt = cartViewModel.state.collectAsState().value.itemsCnt
                )
            },

            modifier = Modifier.fillMaxSize()

        ) {
            innerPadding ->
            NavHost(navController, startDestination = Routes.CATALOG)
            {
                composable(Routes.CATALOG)
                {
                    Catalog(
                        cartViewModel = cartViewModel,
                        productsContentHandler = apiProductsContentHandler,
                        modifier = Modifier
                            .padding((innerPadding))
                    )
                }
                composable(Routes.CART)
                {
                    Cart(
                        cartViewModel = cartViewModel,
                        modifier = Modifier
                            .padding((innerPadding))
                    )
                }

            }
        }
    }
}




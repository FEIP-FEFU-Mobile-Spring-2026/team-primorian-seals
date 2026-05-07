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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sealsmarket.data.ProductsData.AssetsProductsContentHandler
import com.example.sealsmarket.data.ProductsData.ExampleProductsContentHandler
import com.example.sealsmarket.navigation.Routes
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.cart.Cart
import com.example.sealsmarket.ui.cart.CartTopBar
import com.example.sealsmarket.ui.catalog.Catalog
import com.example.sealsmarket.ui.theme.SealsMarketTheme

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

        val assetsJsonReader = AssetsJsonReader(this);

        val assetsProductsContentHandler = AssetsProductsContentHandler(assetsJsonReader)

        Scaffold(
            topBar = {
                when (currentRoute) {
                    Routes.CART -> { CartTopBar() }
                    Routes.CATALOG -> {
                    }
                }
            },

            bottomBar = {
                NavigationPanel(
                    onCatalogNavigate = { navController.navigate(Routes.CATALOG){launchSingleTop=true} },
                    onCartNavigate = { navController.navigate(Routes.CART){launchSingleTop=true} },
                    curRoute = currentRoute
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
                        productsContentHandler = assetsProductsContentHandler,
                        modifier = modifier
                            .padding((innerPadding))
                    )
                }
                composable(Routes.CART)
                {
                    Cart(
                        modifier = modifier
                            .padding((innerPadding))
                    )
                }

            }
        }
    }
}




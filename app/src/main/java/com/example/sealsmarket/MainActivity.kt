package com.example.sealsmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.cart.Cart
import com.example.sealsmarket.ui.cart.CartTopBar
import com.example.sealsmarket.ui.catalog.Catalog
import com.example.sealsmarket.ui.theme.SealsMarketTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SealsMarketTheme {
                App()
            }
        }
    }
    @Composable
    fun App(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        Scaffold(
            topBar = {
                when (currentRoute) {
                    "cart" -> { CartTopBar() }
                    "catalog" -> {

                    }
                }
            },
            bottomBar = {
                NavigationPanel(
                    onCatalogNavigate = { navController.navigate("catalog"){launchSingleTop=true} },
                    onCartNavigate = { navController.navigate("cart"){launchSingleTop=true} }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            NavHost(navController, startDestination = "catalog") {
                composable("catalog") {
                    Catalog(
                        modifier = modifier
                            .padding((innerPadding))
                    )
                }
                composable("cart") {
                    Cart(
                        modifier = modifier
                            .padding((innerPadding))
                    )
                }

            }
        }
    }
}




package com.example.sealsmarket.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sealsmarket.R
import com.example.sealsmarket.navigation.Routes
import com.example.sealsmarket.ui.cart.CartViewModel

@Composable
fun NavigationPanel(
    onCatalogNavigate: () ->Unit,
    onCartNavigate: ()->Unit,
    modifier: Modifier = Modifier,
    curRoute: String? = "") {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        NavButton(
            Icons.Outlined.Menu,
            Icons.Filled.Menu,
            R.string.menu,
            isActive = curRoute == Routes.CATALOG) { onCatalogNavigate() }
        Spacer(modifier = Modifier.width(64.dp))
        NavButton(
            Icons.Filled.ShoppingCart,
            Icons.Outlined.ShoppingCart,
            R.string.cart,
            isActive = curRoute == Routes.CART) { onCartNavigate() }
    }
}

@Composable
fun NavButton(
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    textId: Int,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(35.dp)
        ) {
            Icon(
                imageVector = if (isActive) selectedIcon else unselectedIcon,
                tint = if(isActive) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary,
                contentDescription = null,
                modifier = if(isActive) Modifier.size(29.dp) else Modifier.size(24.dp)
            )
        }
        Text(
            text = stringResource(textId),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun PanelPreview(){
    Scaffold(
        bottomBar =  {NavigationPanel({},{})}
    ){innerPadding->
        Text(text="", modifier = Modifier.padding(innerPadding))
    }

}
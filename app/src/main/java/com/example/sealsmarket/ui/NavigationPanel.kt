package com.example.sealsmarket.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
    cartItemsCnt: Int,
    modifier: Modifier = Modifier,
    curRoute: String? = "") {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
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
            cnt = cartItemsCnt,
            isActive = curRoute == Routes.CART) { onCartNavigate() }
    }
}

@Composable
fun NavButton(
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    textId: Int,
    isActive: Boolean,
    cnt: Int = 0,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(modifier = modifier.size(50.dp)) {
            //Счётчик
            if (cnt > 0) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(ShapeDefaults.Large)
                        .align(Alignment.TopEnd)
                        .background(color = MaterialTheme.colorScheme.secondary)
                ) {

                    Text(
                        text = cnt.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center)
            ) {
                Icon(
                    imageVector = if (isActive) selectedIcon else unselectedIcon,
                    tint = if (isActive) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary,
                    contentDescription = null,
                    modifier = if (isActive) Modifier.size(29.dp) else Modifier.size(24.dp)
                )
            }

            Text(
                text = stringResource(textId),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 4.dp)

            )
        }
    }
}
@Preview
@Composable
fun PanelPreview(){
    Scaffold(
        bottomBar =  {NavigationPanel({},{}, 1)}
    ){innerPadding->
        Text(text="", modifier = Modifier.padding(innerPadding))
    }

}
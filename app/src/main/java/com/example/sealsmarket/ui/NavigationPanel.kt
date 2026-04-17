package com.example.sealsmarket.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sealsmarket.R

@Composable
fun NavigationPanel(
    onCatalogNavigate: () ->Unit,
    onCartNavigate: ()->Unit,
    modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        NavButton(Icons.Filled.Menu, R.string.menu) { onCatalogNavigate() }
        Spacer(modifier = Modifier.width(64.dp))
        NavButton(Icons.Filled.ShoppingCart, R.string.cart) { onCartNavigate() }
    }
}

@Composable
fun NavButton(
    image: ImageVector,
    textId: Int,
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
                .size(32.dp)
        ) {
            Icon(
                imageVector = image,
                contentDescription = null,
            )
        }
        Text(
            text = stringResource(textId),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
package com.example.sealsmarket.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sealsmarket.R
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme
import androidx.compose.runtime.collectAsState
import com.example.sealsmarket.model.CartItem

@Composable
    fun Cart(
        cartViewModel: CartViewModel,
        modifier: Modifier = Modifier){
        val cartState = cartViewModel.state.collectAsState().value
        if(cartState.items.count() == 0) {
            CartContentEmpty(modifier)
        }
    else{
            CartContent(cartState.items, cartViewModel=cartViewModel, modifier)
        }
    }

    @Composable
    fun CartTopBar(onCartClear: ()->Unit, modifier: Modifier = Modifier){
        Box(
            modifier = modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            Text(
                text = stringResource(R.string.cart),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            IconButton(
                onClick = onCartClear,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    contentDescription = stringResource(R.string.bin_description),
                    imageVector = Icons.Outlined.Delete,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }


    @Composable
    fun CartContent(itemList: List<CartItem>, cartViewModel: CartViewModel, modifier: Modifier = Modifier){
        LazyColumn() {
            items(itemList){
                item -> CardContent(
                item,
                onItemDecrement = {cartViewModel.removeItem(item)},
                onItemIncrement = {cartViewModel.addItem(item)},
                onItemRemove = {cartViewModel.removeItem(item, item.count)})
            }
        }
    }

    @Composable
    fun CartContentEmpty(modifier:Modifier = Modifier){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
            ) {
            //Цвета нужно настроить, при чём с учётом тёмной темы. Можно использовать и не secondary
            Text(
                text = stringResource(R.string.cart_empty),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.W500
            )
            Image(
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                painter = painterResource(R.drawable.cart_empty),
                modifier = Modifier
                    .padding(end = 8.dp)

            )

        }
    }

    @Preview
    @Composable
    fun CartPreview(){
        SealsMarketTheme() {
            Scaffold(
                topBar = {CartTopBar({})},
                bottomBar = { NavigationPanel({},{}) }
            ){innerPadding->
                Cart(
                    cartViewModel = viewModel(),
                    modifier = Modifier.padding(innerPadding))
            }
        }
    }

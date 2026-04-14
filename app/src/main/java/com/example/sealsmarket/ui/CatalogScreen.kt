package com.example.sealsmarket.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import com.example.sealsmarket.ui.theme.SealsMarketTheme

class CatalogScreen {
}

@Composable
fun Catalog(modifier: Modifier = Modifier){

}

@Composable
fun Content(modifier: Modifier = Modifier){
  //Список товаров
}

@Composable
fun ItemCard(
    item: Item,
    placeHolder: Painter,
    modifier: Modifier = Modifier){
    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = modifier) {
        Row() {
            AsyncImage(
                contentDescription = null,
                model = item.imageUrl,
                placeholder = placeHolder,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ).height(150.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = item.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Button(
                    onClick = {},
                    shape = MaterialTheme.shapes.extraSmall)
                {
                    Text(text = "${item.priceInKopecks / 100} ₽")
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun CatalogPreview(){
//        SealsMarketTheme() {
//            Scaffold(
//                modifier = Modifier.fillMaxSize()){ innerPadding ->
//                Catalog(modifier = Modifier.padding((innerPadding)))
//            }
//        }
//}

@Composable
@Preview
fun CardPreview(){
    SealsMarketTheme() {
            ItemCard(
                item = emptyItem,
                placeHolder = painterResource(R.drawable.empty),
                )
    }
}
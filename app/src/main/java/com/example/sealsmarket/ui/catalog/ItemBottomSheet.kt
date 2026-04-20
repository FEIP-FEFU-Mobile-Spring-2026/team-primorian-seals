package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.materialcore.screenHeightDp
import coil.compose.AsyncImage
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import kotlinx.serialization.json.Json.Default.configuration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInfo(item: Item, modifier: Modifier = Modifier.navigationBarsPadding()){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
        Box(
            modifier = modifier
                .height(screenHeight*0.8f)
                .background(Color.White)
        ) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                AsyncImage(
                    contentDescription = null,
                    model = item.imageUrl,
                    placeholder = painterResource(R.drawable.empty),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                )
                Text(
                    text = item.longDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                )
            }

            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                LazyRow(contentPadding = PaddingValues(bottom = 16.dp)) {
                    items(item.sizes){
                        size -> SizeButton(
                        size = size,
                            modifier = Modifier.padding(horizontal = 2.dp))
                    }
                }
                Button(
                    onClick = {/*Добавить в корзину*/ },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                        .height(47.dp)) {
                    Text(text = stringResource(R.string.addToCart) + " · ${item.priceInKopecks/100}₽")
                }
            }
        }
}
@Composable
fun SizeButton(
    size: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {/*Выбрать этот размер*/},
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier){
        Text(text = size)
    }
}
@Preview
@Composable
fun WidnowPreview(){
    ItemInfo(emptyItem)
}

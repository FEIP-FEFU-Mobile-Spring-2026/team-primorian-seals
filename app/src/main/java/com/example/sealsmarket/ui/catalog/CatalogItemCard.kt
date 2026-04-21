package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import com.example.sealsmarket.ui.theme.SealsMarketTheme

@Composable
    fun ItemCardContent(
        item: Item,
        onButtonClick: ()->Unit,
        modifier: Modifier = Modifier,
        placeHolder: Painter = painterResource(R.drawable.empty),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.DarkGray
            ),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    contentDescription = null,
                    model = item.imageUrl,
                    placeholder = placeHolder,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .padding(start = 4.dp),
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
                    ItemInfo(
                        item.name,
                        item.shortDescription
                    )

                    Button(
                        onClick = onButtonClick,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    {
                        Text(
                            text = "${item.priceInKopecks / 100} ₽",
                            style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }

@Composable
fun ItemInfo(
    name:String,
    shortDescription: String,
    modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top= 8.dp, bottom = 4.dp)
        )
        Text(
            text = shortDescription,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
@Composable
@Preview
fun CardPreview(){
    SealsMarketTheme() {
            ItemCardContent(
                item = emptyItem,
                {}
                )
    }
}


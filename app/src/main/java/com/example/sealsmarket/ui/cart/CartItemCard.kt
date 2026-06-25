package com.example.sealsmarket.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sealsmarket.R
import com.example.sealsmarket.model.emptyCartItem
import com.example.sealsmarket.model.CartItem
import com.example.sealsmarket.ui.CounterButton
import com.example.sealsmarket.ui.theme.SealsMarketTheme

@Composable
fun CardContent(
    item: CartItem,
    onItemIncrement: ()->Unit,
    onItemDecrement: ()->Unit,
    onItemRemove: ()->Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
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
        Box()
        {
            IconButton(
                onClick = onItemRemove,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp, top = 8.dp)
                    .size(24.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = Color.Gray,
                    contentDescription = stringResource(R.string.removeItemAll)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
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
                    modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        )
                        .height(150.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    ItemInfo(
                        item.name,
                        item.size.name,
                        item.color
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = "${(item.priceInKopecks * item.count / 100)} ₽"
                        )
                        CounterButton(onItemIncrement,
                            onItemDecrement,
                            item.count
                        )
                    }
                }
            }
        }
    }
    }

@Composable
fun ItemInfo(
    name: String,
    size: String,
    color: String,
    modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )

        Text(
            text = color,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = size,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
@Preview
fun CardPreview(){
    SealsMarketTheme() {
        CardContent(
            item = emptyCartItem,
            {},{},{}
        )
    }
}


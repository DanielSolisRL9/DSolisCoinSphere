package com.example.coinsphere

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.coinsphere.models.Coins
import com.example.coinsphere.models.coins
import com.example.coinsphere.ui.theme.Background
import com.example.coinsphere.ui.theme.CoinSphereTheme
import com.example.coinsphere.ui.theme.Surface
import com.example.coinsphere.ui.theme.TextDim
import com.example.coinsphere.ui.theme.TextMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinSphereTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoinSphere(innerPadding)
                }
            }
        }
    }
}

@Composable
fun CoinSphere(innerPadding:PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Text(
            text = "CoinSphere",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextMain,
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
        )

        Column (
            modifier = Modifier
                .padding(top = 10.dp)
        ){
            card(title = "Global Market Cap", description = "$2.18T")
            card(title = "Fear & Greed", description = "Neutral (54)")
            card(title = "Altcoin Season", description = "No")
        }

        Row (
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ){
            Text(
                text = "# Name",
                fontSize = 17.sp,
                color = TextDim,
                modifier = Modifier
                    .weight(2f)


            )
            Text(
                text = "Price",
                fontSize = 17.sp,
                color = TextDim,
                modifier = Modifier
                    .weight(2f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Surface)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(Coins) { coin ->
                CoinRow(coin)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}

@Composable
fun card(title : String, description : String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 20.dp, vertical = 5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = title,
                    fontSize = 19.sp,
                    color = Color.Gray
                )
                Text(
                    text = description,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDim,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }
        }
    }
}

@Composable
fun CoinRow(coin: coins) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1A2333))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = coin.number.toString(),
            color = TextDim,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.5f)
        )

        AsyncImage(
            model = coin.image,
            contentDescription = coin.name,
            modifier = Modifier
                .size(24.dp)
                .weight(0.7f, fill = false)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = coin.name,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.weight(2f)
        )

        Text(
            text = coin.price,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(2f),
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinSphereTheme {
        CoinSphere(innerPadding = PaddingValues(all = 10.dp))
    }
}
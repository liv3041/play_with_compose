package com.toonandtools.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toonandtools.playingwithcompose.R
import com.toonandtools.playingwithcompose.ui.theme.PlayComposeTheme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayComposeTheme   {
                SpaceCreation()
            }
        }
    }
}


@Composable
fun SpaceCreation( ) {

    val data = listOf(
        Triple("Floating Balloon in the Sky","Bubbly Balloon", R.drawable.hot_air_balloon),
        Triple("Pastures and Breeze","Greeny Green", R.drawable.mountain),
        Triple("Land of infinite Sand","Sandy Sand", R.drawable.desert)
    )

    // Mutable state to track the current index
    var currentIndex by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()

    ) {
        Image(painter = painterResource(data[currentIndex].third),
            contentDescription = "${data[currentIndex].first} + Image" ,
            modifier = Modifier.padding(50.dp))
        Text(text = data[currentIndex].first)
        Text(text = data[currentIndex].second)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else data.lastIndex
            },
                modifier = Modifier
                    .padding(16.dp) // Add padding around the button
                    .size(width = 120.dp, height = 50.dp) // Set size for the button
            ){
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                // Navigate to the next item
                currentIndex = if (currentIndex < data.lastIndex) currentIndex + 1 else 0
            },
                modifier = Modifier
                    .padding(16.dp) // Different padding
                    .size(width = 120.dp, height = 50.dp)){
                Text(text = "Next")

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
   PlayComposeTheme {
        SpaceCreation()
    }
}
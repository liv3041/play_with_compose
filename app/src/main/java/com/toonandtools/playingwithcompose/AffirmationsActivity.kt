package com.toonandtools.playingwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toonandtools.playingwithcompose.data.Affirmation
import com.toonandtools.playingwithcompose.data.DataSource
import com.toonandtools.playingwithcompose.data.Topic

import com.toonandtools.playingwithcompose.ui.theme.PlayComposeTheme

class AffirmationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayComposeTheme{
                App()
            }
        }
    }
}

@Composable
private fun App(){
    var isListVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Switch button to toggle between ImageView and TextView
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if(isListVisible) "List View" else "Grid View",
                style =  MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(8.dp)
                    .clickable {
                    // Change the state of the switch when the text is clicked
                    isListVisible= !isListVisible
                }
            )
            Switch(
                checked = isListVisible,
                onCheckedChange = { isListVisible = it },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))
        if(isListVisible){
            AffirmationApp()
        }else{
            HobbiesApp()
        }
    }
}


@Composable
private fun AffirmationApp () {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection)
            ),
    ) {
       AffirmationList(
           affirmationList = DataSource().loadAffirmations(),
       )
    }

}
@Composable
private fun HobbiesApp () {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection)
            ),
    ) {
        TopicGrid(
            topicList = DataSource().loadHobbies(),
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
            )
        )

    }

}


@Composable
private fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
 Card(modifier = modifier) {
     Column {
         Image(
             painter = painterResource(affirmation.imageResourceId),
             contentDescription = stringResource(affirmation.stringResourceId),
             modifier = Modifier
                 .fillMaxWidth()
                 .height(194.dp),
             contentScale = ContentScale.Crop
         )
         Text(
             text = LocalContext.current.getString(affirmation.stringResourceId),
             modifier = Modifier.padding(16.dp),
             style = MaterialTheme.typography.headlineSmall
         )
     }
 }
}

@Composable
private fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(
                            width = 68.dp,
                            height = 68.dp
                        )
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

                Column {
                    Text(
                        text = stringResource(id = topic.name),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        ),
                    )
                    Row (verticalAlignment = Alignment.CenterVertically){

                        Icon(
                            painter = painterResource(R.drawable.ic_grain),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = dimensionResource(R.dimen.padding_medium))
                        )
                        Text(
                            text = topic.availableCourses.toString(),
                            modifier = Modifier.padding(
                                start = dimensionResource(R.dimen.padding_small)
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }


                }

            }




        }

}
@Composable
fun AffirmationList(affirmationList: List<Affirmation>,modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier){
        items(affirmationList) {affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Composable
fun TopicGrid(topicList: List<Topic>,modifier: Modifier = Modifier) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier

    ){
        items(topicList) {topic->
            TopicCard(
                topic = topic,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationAppPreview() {
    PlayComposeTheme {

        App()
    }
}
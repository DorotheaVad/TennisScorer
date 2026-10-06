package com.example.tennisscorer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tennisscorer.logic.TennisMatch
import com.example.tennisscorer.logic.TennisPlayer
import com.example.tennisscorer.ui.theme.TennisScorerTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val player1= TennisPlayer("Me")
        val player2= TennisPlayer("You")
        val match= TennisMatch(player1,player2)
        setContent {
            TennisScorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    MatchDisplay(match)

                }
            }
        }
    }
}

@Composable
fun MatchDisplay( match: TennisMatch) {

    val numberOfSets = if (match.bestOf3) 3 else 5
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(10.dp)) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        "Match",
                        modifier = Modifier.padding(7.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.fillMaxWidth()) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Row(horizontalArrangement = Arrangement.Center) {
                            Text("Player",modifier=Modifier.weight(1f))

                            repeat(numberOfSets) { set ->
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Set ${set + 1}", modifier=Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(match.player1.name, modifier = Modifier.weight(1f))

                            repeat(numberOfSets) { set ->
                                Spacer(modifier = Modifier.width(5.dp),)
                                Text("0", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(match.player2.name,modifier=Modifier.weight(1f))
                            repeat(numberOfSets) { set ->
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("0", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(10.dp)){
                            Text("Current Game", style = MaterialTheme.typography.titleMedium )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row() {
                            Text(match.player1.name,modifier=Modifier.weight(1f), textAlign = TextAlign.Center,style= MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(match.player2.name,modifier=Modifier.weight(1f), textAlign = TextAlign.Center,style= MaterialTheme.typography.titleSmall)

                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(){
                            Text("${match.currentSet.currentGame!!.player1Score}",modifier=Modifier.weight(1f), textAlign = TextAlign.Center,style= MaterialTheme.typography.displayMedium)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text("${match.currentSet.currentGame!!.player2Score}",modifier=Modifier.weight(1f), textAlign = TextAlign.Center,style= MaterialTheme.typography.displayMedium)

                        }
                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MatchDisplayPreview() {
    val player1= TennisPlayer("Me")
    val player2= TennisPlayer("You")
    val match= TennisMatch(player1,player2,true)
    TennisScorerTheme {
        MatchDisplay(match)
    }
}
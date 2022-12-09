package com.plandel.themovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.plandel.themovieapp.navigation.navGraph.SetupNavGraph
import com.plandel.themovieapp.ui.theme.TheMovieAppTheme
import com.plandel.themovieapp.ui.theme.colorBottomBar
import com.plandel.themovieapp.ui.theme.colorButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieAppTheme {

                Scaffold(bottomBar = {
                    BottomBarSession()
                }) {
                    val navController = rememberNavController()
                    SetupNavGraph(navController)
                }
            }
        }
    }

    @Composable
    private fun BottomBarSession() {
        BottomAppBar(
            modifier = Modifier.height(70.dp),
            elevation = 6.dp,
            backgroundColor = colorBottomBar
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                buttonMovieBottomBar(title = "Movie") {}
            }
        }
    }
}



@Composable
fun buttonMovieBottomBar(
    modifier: Modifier = Modifier,
    title: String,
    onItemClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colorButton)
            .padding(vertical = 8.dp, horizontal = 18.dp)
            .clickable { onItemClick() },
        contentAlignment = Alignment.Center
    ) {
        Row {
           Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription = "Play", tint = Color.White )
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

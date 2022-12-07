package com.plandel.themovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.plandel.themovieapp.navigation.navGraph.SetupNavGraph
import com.plandel.themovieapp.ui.theme.TheMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}

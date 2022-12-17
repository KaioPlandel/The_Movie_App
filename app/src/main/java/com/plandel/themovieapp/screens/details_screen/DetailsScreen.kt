package com.plandel.themovieapp.screens.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.plandel.themovieapp.model.Movie
import com.plandel.themovieapp.screens.main_screen.CategoriesSession
import com.plandel.themovieapp.ui.theme.colorBackground
import com.plandel.themovieapp.ui.theme.colorButton
import com.plandel.themovieapp.util.Constants.BASE_URL

@Composable
fun DetailsScreen(
    navController: NavController,
    id: Int?,
    modifier: Modifier = Modifier,
    model: DetailsViewModel = hiltViewModel()
) {
    id?.let {
        model.getMovieById(it)
    }
    val state by model.movie.collectAsState()
    if (state.isEmpty()) {
        CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    } else {
        ImageSession(movie = state.component1())
    }
}

@Composable
fun ImageSession(modifier: Modifier = Modifier, movie: Movie) {
    val painter = rememberAsyncImagePainter(model = "${BASE_URL}${movie.image}")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorBackground)
    ) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            elevation = 4.dp,
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .height(380.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "image_movie",
                modifier = Modifier,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = modifier.padding(5.dp))
        RatingSession(movie = movie)
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = MaterialTheme.typography.h5.fontSize,
            modifier = modifier
                .padding(start = 13.dp, top = 5.dp, bottom = 5.dp)

        )
      //  CategoriesSession(categories = movie.categories, modifier = Modifier.padding(start = 13.dp))
        DescriptionSession(movie = movie)
    }
}

@Composable
fun DescriptionSession(modifier: Modifier = Modifier, movie: Movie) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp, top = 8.dp)
    ) {
        Text(
            text = movie.description,
            color = Color.DarkGray,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            modifier = modifier
                .padding(horizontal = 13.dp)
        )
    }
}

@Composable
private fun RatingSession(movie: Movie) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "star",
            tint = Color.Yellow,
        )
        Text(
            text = movie.rating.toString(),
            color = colorButton,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}
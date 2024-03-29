package com.plandel.themovieapp.screens.main_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.plandel.themovieapp.R
import com.plandel.themovieapp.model.Item
import com.plandel.themovieapp.model.Movie
import com.plandel.themovieapp.model.User
import com.plandel.themovieapp.navigation.screens.MovieScreen
import com.plandel.themovieapp.ui.theme.colorBackground
import com.plandel.themovieapp.ui.theme.colorButton
import com.plandel.themovieapp.ui.theme.colorSearchItem
import com.plandel.themovieapp.util.Constants.BASE_URL

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    model: MainViewModel = hiltViewModel()
) {

    val state by model.moviesA.collectAsState()
    val stateTop by model.moviesTop.collectAsState()
    val stateFavorite by model.moviesFavorite.collectAsState()
    val stateSearch by model.moviesSearch.collectAsState()

    var menuItem by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorBackground)
            .padding(start = 12.dp, end = 12.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }
        val user = User(name = "Kaio Plandel", painterResource(id = R.drawable.profile))
        SessionTop(user = user, text = text, onValue = {
            text = it
        })
        Spacer(modifier = Modifier.padding(5.dp))
        if (text.isNotEmpty()) {
            model.searchMovie(text)
            if (stateSearch.isNotEmpty()) {
                GridScreen(navController = navController,movies = stateSearch)
            }
        } else {
            Spacer(modifier = modifier.height(16.dp))
            TitleSession(title = "Categories")
            CategoriesSession(
                categories = listOf(
                    Item(1, "Action"),
                    Item(2, "Comedy"),
                    Item(3, "Horror"),
                    Item(4, "Adventuri"),
                )
            ) {
                menuItem = it
            }

            if (menuItem.isNotEmpty()) {
                if (menuItem == "Action") {
                    if (stateTop.isEmpty()) Progressbar()
                    GridScreen(navController = navController,movies = stateTop, modifier = Modifier.padding(top = 15.dp))
                } else if (menuItem == "Comedy") {
                    if (stateTop.isEmpty()) Progressbar()
                    GridScreen(navController = navController,movies = state, modifier = Modifier.padding(top = 15.dp))
                } else if (menuItem == "Horror") {
                    if (stateTop.isEmpty()) Progressbar()
                    GridScreen(navController = navController,movies = stateFavorite, modifier = Modifier.padding(top = 15.dp))
                } else if (menuItem == "Adventuri") {
                    if (stateTop.isEmpty()) Progressbar()
                    GridScreen(navController = navController,movies = stateTop, modifier = Modifier.padding(top = 15.dp))
                }
            } else {
                if (state.isEmpty() && stateTop.isEmpty() && stateFavorite.isEmpty()) {
                    Progressbar()
                }
                Spacer(modifier = Modifier.padding(3.dp))
                MoviesSession(
                    navController = navController,
                    lasMovies = state,
                    topMovies = stateTop,
                    favoriteMovies = stateFavorite
                )
            }
        }
    }
}

@Composable
private fun Progressbar() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun SessionTop(
    user: User,
    text: String,
    onValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Welcome ${user.name}!", style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = "Let's relax and watch a movie.", style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            RoundImage(image = user.image)
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))
        SearchItem(text = text, onValue = onValue)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    text: String,
    onValue: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp,
        color = colorSearchItem,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(30.dp)
            )
            TextField(
                value = text,
                onValueChange = onValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorSearchItem),
                maxLines = 1,
                label = {
                    Text(text = "Search movie..")
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    textColor = Color.White
                )
            )
        }
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        elevation = 5.dp,
        modifier = modifier
            .size(50.dp)
    ) {
        Image(
            painter = image,
            contentDescription = stringResource(R.string.image_profile),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CategoriesSession(
    modifier: Modifier = Modifier,
    categories: List<Item>,
    onItemClick: (String) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Column {
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
        ) {
            items(categories) { category ->
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(
                            if (category.id == selectedIndex) Color.Blue else colorSearchItem
                        )
                        .selectable(
                            selected = category.id == selectedIndex,
                            onClick = {
                                if (selectedIndex != category.id) {
                                    selectedIndex = category.id
                                    onItemClick(category.title)
                                } else {
                                    selectedIndex = -1
                                    onItemClick("")
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.title,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
                    )
                }
            }
        }

    }
}

@Composable
private fun TitleSession(modifier: Modifier = Modifier, title: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "View all",
            fontSize = MaterialTheme.typography.caption.fontSize,
            color = colorButton
        )
    }
}

@Composable
fun MoviesSession(
    navController: NavController,
    modifier: Modifier = Modifier,
    lasMovies: List<Movie>,
    topMovies: List<Movie>,
    favoriteMovies: List<Movie>,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        SessionMovieCategory(
            navController = navController,
            title = "Lasted Movie",
            movies = lasMovies
        )
        SessionMovieCategory(
            navController = navController,
            title = "Top Movies",
            movies = topMovies
        )
        SessionMovieCategory(
            navController = navController,
            title = "Favorite Movie",
            movies = favoriteMovies
        )
    }
}

@Composable
private fun SessionMovieCategory(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String,
    movies: List<Movie>
) {
    TitleSession(title = title, modifier = Modifier.padding(top = 30.dp))
    Spacer(modifier = modifier.height(5.dp))
    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(movies) { aMovie ->
            MovieBox(movie = aMovie, onMovieClick = {
                navController.navigate(MovieScreen.DetailsScreen.withArgrs(it.id))
            })
        }
    }
}


@Composable
fun MovieBox(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    val painter = rememberAsyncImagePainter(model = "$BASE_URL${movie.image}")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = 5.dp,
            modifier = modifier
                .padding(6.dp)
                .height(180.dp)
                .width(120.dp)
                .clickable { onMovieClick(movie) }
        ) {
            Image(
                painter = painter,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }
        Text(text = movie.title, color = Color.White, textAlign = TextAlign.Center)
    }
}

@Composable
fun GridScreen(navController: NavController,modifier: Modifier = Modifier, movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(movies) { movie ->
            MovieBox(movie = movie, onMovieClick = {navController.navigate(MovieScreen.DetailsScreen.withArgrs(movie.id))})
        }
    }
}









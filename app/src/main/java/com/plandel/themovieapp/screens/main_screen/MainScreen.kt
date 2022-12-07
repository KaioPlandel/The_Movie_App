package com.plandel.themovieapp.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plandel.themovieapp.R
import com.plandel.themovieapp.model.User
import com.plandel.themovieapp.ui.theme.colorBackground
import com.plandel.themovieapp.ui.theme.colorSearchItem

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorBackground)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember { mutableStateOf("") }

        val user = User(name = "Kaio Plandel", painterResource(id = R.drawable.img))

        SessionTop(user = user, text = text, onValue = { text = it })
    }
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

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    text: String,
    onValue: (String) -> Unit
) {
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
                modifier = Modifier.fillMaxWidth()
                    .background(colorSearchItem),
                maxLines = 1,
                label = {
                    Text(text = "Search movie..")
                }
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









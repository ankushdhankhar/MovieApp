package com.example.movieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.Navigation.MovieScreens
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies", modifier = Modifier.padding(vertical = 4.dp , horizontal = 110.dp) , style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)  },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .padding(6.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(corner = CornerSize(10.dp))
                    )
            )
        }
    ) {
        MainContent(navController=navController)

    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column (modifier = Modifier.padding(start = 10.dp, top = 70.dp, end = 5.dp, bottom = 5.dp)){
        LazyColumn() {
            items(items = movieList){
                MovieRow(movie = it ){movie->
                   navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
//                    Log.d("TAg", "Main Content:$movie")
                }
            }
        }
    }

}
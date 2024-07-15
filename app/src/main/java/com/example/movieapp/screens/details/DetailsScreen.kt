package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    movieId: String?){
    var newMovieList = getMovies().filter { movie -> movie.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies" ,  modifier = Modifier.padding(vertical = 4.dp , horizontal = 0.dp) , style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)},
            colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.background
                    ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(start = 0.dp, top = 0.dp, end = 105.dp, bottom = 0.dp))
                })


        }) {
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 10.dp, top = 70.dp, end = 5.dp, bottom = 5.dp)) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {
                MovieRow(movie=newMovieList.first())

                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 2.dp)

                Text(text = "Movie Images" , style = MaterialTheme.typography.headlineMedium , fontWeight = FontWeight.Bold ,
                    modifier = Modifier.padding(top = 10.dp))
                HorizontalScrollableMovieImagesView(newMovieList)
            }
        }
    }

    




//        Surface(modifier = Modifier.fillMaxSize()) {
//            Column(horizontalAlignment =Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//                Text(text = movieData.toString() , style = MaterialTheme.typography.displayMedium)
//                Spacer(modifier = Modifier.height(23.dp))
//                Button(onClick = {
//                    navController.popBackStack()
//                }) {
//                    Text(text = "Go Back")
//                }
//            }
//        }
//    }

}

@Composable
private fun HorizontalScrollableMovieImagesView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .height(168.5.dp)
                    .width(300.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "images"
                )
            }
        }
    }
}


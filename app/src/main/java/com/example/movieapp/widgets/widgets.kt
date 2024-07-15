package com.example.movieapp.widgets

import android.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies


@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick:(String)->Unit ={}){
    var expanded by remember{
        mutableStateOf(false)
    }
    Card(
        Modifier
            .padding(7.dp)
            .fillMaxWidth()
//            .height(110.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {

            Surface(modifier = Modifier
                .padding(2.dp)
                .height(100.dp)
                .width(170.dp),
                shape = RoundedCornerShape(corner = CornerSize(30.dp)),
                shadowElevation = 10.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.images[0]),
                    contentDescription = "movie poster"
                )
//                Icon(imageVector = Icons.TwoTone.PlayArrow, contentDescription ="Movie image" )
            }
            Column(modifier = Modifier.padding(1.dp)){
                Text(text = movie.title ,style=MaterialTheme.typography.headlineSmall)
                Text(text = "Director :${movie.director}",style=MaterialTheme.typography.labelLarge)
                Text(text = "Released :${movie.year}",style=MaterialTheme.typography.labelLarge)

                AnimatedVisibility(visible = expanded) {
                    Column {

                        Text(text = "Actors: ${movie.actors}",style=MaterialTheme.typography.labelLarge)
                        Text(text = "Rating: ${movie.rating}",style=MaterialTheme.typography.labelLarge)

                        Divider()

                        Text(buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.Cyan,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.W900)){
                                append("Plot: ")
                            }
                            withStyle(SpanStyle(
                                color=Color.Magenta,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)){
                                append(movie.plot)
                            }

                        }, modifier = Modifier.padding(6.dp))


                    }
                }

                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown, contentDescription ="arrowdown" ,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        } ,
                    tint = Color.LightGray)
            }
        }
    }
}


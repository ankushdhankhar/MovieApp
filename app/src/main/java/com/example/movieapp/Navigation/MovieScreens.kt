package com.example.movieapp.Navigation

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;
    companion object{
        fun fromRoute(route:String?):MovieScreens
        =when(route?.substringBefore("/")){
            HomeScreen.name->HomeScreen
            DetailsScreen.name->DetailsScreen
            null->HomeScreen
            else->throw IllegalArgumentException("route $route is not recognised")
        }
    }
}
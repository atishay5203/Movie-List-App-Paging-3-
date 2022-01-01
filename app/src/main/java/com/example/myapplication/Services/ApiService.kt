package com.example.myapplication.Services

import com.example.myapplication.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_URL= "http://image.tmdb.org/t/p/w185"
        const val TMDB_API_KEY="585b080d502a6d7f117b88039d9543c9"
    }

    @GET("discover/movie?certification_country=US&adult=false&vote_count.gte=100&" +
            "with_original_language=en&sort_by=primary_release_date.desc")

    suspend fun getAllMovies(@Query("page") page:Int): Response<MovieResponse>
}
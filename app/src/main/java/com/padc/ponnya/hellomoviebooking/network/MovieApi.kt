package com.padc.ponnya.hellomoviebooking.network

import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.network.responses.MovieCreditResponse
import com.padc.ponnya.hellomoviebooking.network.responses.MovieListResponse
import com.padc.ponnya.hellomoviebooking.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(API_NOW_PLAYING_MOVIES)
    fun nowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET(API_COMING_SOON_MOVIES)
    fun comingSoonMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET("$API_MOVIE_DETAIL/{movie_id}")
    fun movieDetail(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<MovieVO>

    @GET("$API_MOVIE_CREDIT/{movie_id}/credits")
    fun movieCredit(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Call<MovieCreditResponse>

}
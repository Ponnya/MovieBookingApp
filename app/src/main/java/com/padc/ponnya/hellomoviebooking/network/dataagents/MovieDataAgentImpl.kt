package com.padc.ponnya.hellomoviebooking.network.dataagents

import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.network.MovieApi
import com.padc.ponnya.hellomoviebooking.network.responses.MovieCreditResponse
import com.padc.ponnya.hellomoviebooking.network.responses.MovieListResponse
import com.padc.ponnya.hellomoviebooking.utils.BASE_URL_MOVIE
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieDataAgentImpl : MovieDataAgent {
    private var mMovieApi: MovieApi? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mMovieApi = retrofit.create(MovieApi::class.java)
    }

    override fun nowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi?.nowPlayingMovies()?.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.results ?: listOf())
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun comingSoonMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi?.comingSoonMovies()?.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.results ?: listOf())
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun movieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.movieDetail(movieId = movieId)?.enqueue(object : Callback<MovieVO> {
            override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun movieCredit(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.movieCredit(movieId)?.enqueue(object : Callback<MovieCreditResponse> {
            override fun onResponse(
                call: Call<MovieCreditResponse>,
                response: Response<MovieCreditResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.cast?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }


}
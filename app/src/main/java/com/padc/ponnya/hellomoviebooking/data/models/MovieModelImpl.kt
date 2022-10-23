package com.padc.ponnya.hellomoviebooking.data.models

import android.content.Context
import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO
import com.padc.ponnya.hellomoviebooking.data.vos.COMING_SOON
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.data.vos.NOW_PLAYING
import com.padc.ponnya.hellomoviebooking.network.dataagents.MovieDataAgent
import com.padc.ponnya.hellomoviebooking.network.dataagents.MovieDataAgentImpl
import com.padc.ponnya.hellomoviebooking.persistence.MovieBookingDatabase

object MovieModelImpl : MovieModel {

    private val mMovieDataAgent: MovieDataAgent = MovieDataAgentImpl

    private var mMovieBookingDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieBookingDatabase = MovieBookingDatabase.getDBInstance(context)
    }


    override fun nowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDatabase?.movieDao()?.selectMovieByType(NOW_PLAYING)?.let(onSuccess)
        mMovieDataAgent.nowPlayingMovies(
            onSuccess = {
                it.forEach { movieVO -> movieVO.type = NOW_PLAYING }
                mMovieBookingDatabase?.movieDao()?.insertMovieList(it)
                onSuccess(it)
            },
            onFailure = onFailure,
        )
    }

    override fun comingSoonMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDatabase?.movieDao()?.selectMovieByType(COMING_SOON)?.let(onSuccess)
        mMovieDataAgent.comingSoonMovies(
            onSuccess = {
                it.forEach { movieVO -> movieVO.type = COMING_SOON }
                mMovieBookingDatabase?.movieDao()?.insertMovieList(it)
                onSuccess(it)
            },
            onFailure = onFailure,
        )
    }

    override fun movieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDatabase?.movieDao()?.selectMovieById(movieId.toInt())?.let(onSuccess)
        mMovieDataAgent.movieDetail(
            movieId = movieId,
            onSuccess = {
                val movieFromDatabase =
                    mMovieBookingDatabase?.movieDao()?.selectMovieById(movieId.toInt())
                it.type = movieFromDatabase?.type.toString()
                mMovieBookingDatabase?.movieDao()?.insertMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun movieCredit(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDatabase?.actorDao()?.selectActorsByMovieId(movieId.toInt())?.let(onSuccess)
        mMovieDataAgent.movieCredit(
            movieId = movieId,
            onSuccess = {
                it.forEach { actorVO -> actorVO.movieId = movieId.toInt()  }
                mMovieBookingDatabase?.actorDao()?.insertActorList(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }


}
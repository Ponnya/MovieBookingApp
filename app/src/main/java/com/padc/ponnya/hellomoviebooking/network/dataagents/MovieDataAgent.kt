package com.padc.ponnya.hellomoviebooking.network.dataagents

import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO

interface MovieDataAgent {

    fun nowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun comingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun movieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun movieCredit(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit,
    )
}
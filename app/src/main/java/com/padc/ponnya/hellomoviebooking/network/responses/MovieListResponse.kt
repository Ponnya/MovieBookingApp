package com.padc.ponnya.hellomoviebooking.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.data.vos.DateVO
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?,
)
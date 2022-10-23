package com.padc.ponnya.hellomoviebooking.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO

data class MovieCreditResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("cast")
    val cast: List<ActorVO>?,

    @SerializedName("crew")
    val crew: List<ActorVO>?,
)
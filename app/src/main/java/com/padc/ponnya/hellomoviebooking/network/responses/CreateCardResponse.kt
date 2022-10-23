package com.padc.ponnya.hellomoviebooking.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.data.vos.CardVO
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO

data class CreateCardResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<CardVO>?,
)


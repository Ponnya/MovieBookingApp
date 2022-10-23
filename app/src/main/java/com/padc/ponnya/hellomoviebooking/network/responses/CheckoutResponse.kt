package com.padc.ponnya.hellomoviebooking.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.data.vos.CheckoutVO

data class CheckoutResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: CheckoutVO?,
)

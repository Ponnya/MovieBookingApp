package com.padc.ponnya.hellomoviebooking.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.data.vos.PaymentMethodVO

data class GetPaymentMethodListResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: List<PaymentMethodVO>?,
)
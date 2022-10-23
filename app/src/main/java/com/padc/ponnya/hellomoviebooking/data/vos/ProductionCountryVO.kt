package com.padc.ponnya.hellomoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCountryVO(
    @SerializedName("iso_3166_1")
    val iSO: String?,

    @SerializedName("name")
    val name: String?,
)

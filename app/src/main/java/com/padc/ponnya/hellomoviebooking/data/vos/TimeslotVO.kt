package com.padc.ponnya.hellomoviebooking.data.vos

import com.google.gson.annotations.SerializedName

data class TimeslotVO(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int?,

    @SerializedName("start_time")
    val startTime: String?,

    val isSelected: Boolean? = false,
)

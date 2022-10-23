package com.padc.ponnya.hellomoviebooking.delegate

import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO

interface SelectTimeDelegate {
    fun onTapTime(cinemaDayTimeslotId: Int)
}
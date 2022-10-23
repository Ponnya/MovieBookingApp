package com.padc.ponnya.hellomoviebooking.delegate

import android.view.View
import java.time.LocalDate

interface SelectDateDelegate {
    fun onTapDate(bookingDate: LocalDate)
}
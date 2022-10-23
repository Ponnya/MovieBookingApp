package com.padc.ponnya.hellomoviebooking.delegate

interface SnackButtonDelegate {
    fun onTapPlusButton(snackId: Int)

    fun onTapMinusButton(snackId: Int)
}
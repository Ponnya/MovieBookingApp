package com.padc.ponnya.hellomoviebooking.delegate

interface LoginButtonDelegate {
    fun onTapLoginInButton(
        email: String,
        password: String,
        errorCheck: (Pair<Int?, String?>) -> Boolean,
        errorPass: Boolean,
    )

    fun onTapSignInButton(
        name: String,
        email: String,
        phone: String,
        password: String,
        errorCheck: (Pair<Int?, String?>) -> Boolean,
        errorPass: Boolean,
    )
}
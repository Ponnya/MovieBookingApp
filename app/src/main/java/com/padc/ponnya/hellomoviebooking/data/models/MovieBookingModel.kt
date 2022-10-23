package com.padc.ponnya.hellomoviebooking.data.models

import com.padc.ponnya.hellomoviebooking.data.vos.*
import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import com.padc.ponnya.hellomoviebooking.network.responses.LogoutResponse

interface MovieBookingModel {

    fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<Int?, String?>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<Int?, String?>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun profile(
        onSuccess: (UserInfoVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        onSuccess: (LogoutResponse) -> Boolean,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeslot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun cinemaSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<SeatVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethodList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        checkoutRequest: CheckoutRequest,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    )

}
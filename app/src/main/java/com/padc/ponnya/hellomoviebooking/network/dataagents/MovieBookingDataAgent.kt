package com.padc.ponnya.hellomoviebooking.network.dataagents

import com.padc.ponnya.hellomoviebooking.data.vos.*
import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import com.padc.ponnya.hellomoviebooking.network.responses.LogoutResponse
import com.padc.ponnya.hellomoviebooking.network.responses.RegisterAndLoginWithEmailResponse

interface MovieBookingDataAgent {
    fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (RegisterAndLoginWithEmailResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (RegisterAndLoginWithEmailResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun profile(
        token: String,
        onSuccess: (UserInfoVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        token: String,
        onSuccess: (LogoutResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun cinemaSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<SeatVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethodList(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        token: String,
        checkoutRequest: CheckoutRequest,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    )
}
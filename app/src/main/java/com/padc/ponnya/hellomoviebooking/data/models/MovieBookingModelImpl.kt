package com.padc.ponnya.hellomoviebooking.data.models

import android.content.Context
import com.padc.ponnya.hellomoviebooking.data.vos.*
import com.padc.ponnya.hellomoviebooking.network.dataagents.MovieBookingDataAgent
import com.padc.ponnya.hellomoviebooking.network.dataagents.MovieBookingDataAgentImpl
import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import com.padc.ponnya.hellomoviebooking.network.responses.LogoutResponse
import com.padc.ponnya.hellomoviebooking.persistence.MovieBookingDatabase


object MovieBookingModelImpl : MovieBookingModel {
    private val mMovieBookingDataAgent: MovieBookingDataAgent = MovieBookingDataAgentImpl
    private var mMovieBookingDatabase: MovieBookingDatabase? = null

    var token: String? = null
        private set

    fun initDatabase(context: Context) {
        mMovieBookingDatabase = MovieBookingDatabase.getDBInstance(context)
        token = mMovieBookingDatabase?.userDao()?.selectToken()
    }


    override fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (Pair<Int?, String?>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                token = it.token
                mMovieBookingDatabase?.userDao()?.insertUser(it.data?.copy(token = it.token))
                onSuccess(Pair(it.code, it.message))
            },
            onFailure = onFailure
        )
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<Int?, String?>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDataAgent.loginWithEmail(
            email = email,
            password = password,
            onSuccess = {
                token = it.token
                mMovieBookingDatabase?.userDao()?.insertUser(it.data?.copy(token = it.token))
                onSuccess(Pair(it.code, it.message))
            },
            onFailure = onFailure
        )
    }

    override fun profile(onSuccess: (UserInfoVO) -> Unit, onFailure: (String) -> Unit) {
        /*mMovieBookingDatabase?.userDao()?.selectToken()?.let {
            mMovieBookingDataAgent.profile(
                token = it.transformToBearerToken(),
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }*/
        mMovieBookingDatabase?.userDao()?.selectUser()?.let {
            onSuccess(it)
        }


    }


    override fun logout(onSuccess: (LogoutResponse) -> Boolean, onFailure: (String) -> Unit) {
        mMovieBookingDatabase?.let {
            token?.let { token ->
                mMovieBookingDataAgent.logout(
                    token = token.transformToBearerToken(),
                    onSuccess = { logoutResponse ->
                        if (onSuccess(logoutResponse)) {
                            it.userDao().deleteAllUser()
                            it.movieDao().deleteMovie()
                            it.actorDao().deleteActors()
                            it.snackDao().deleteAllSnack()
                            it.cinemaDao().deleteAllCinema()
                            it.paymentMethodDao().deleteAllPaymentMethodList()
                        }
                        onSuccess(logoutResponse)
                    },
                    onFailure = onFailure
                )
            }
        }
    }

    override fun getCinemaDayTimeslot(
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDatabase?.cinemaDao()?.selectCinemaListByDate(date, movieId.toInt())
            ?.let(onSuccess)
        token?.let { token ->
            mMovieBookingDataAgent.getCinemaDayTimeslot(
                token = token.transformToBearerToken(),
                movieId = movieId,
                date = date,
                onSuccess = {
                    it.forEach { cinemaVO ->
                        cinemaVO.date = date
                        cinemaVO.movieId = movieId.toInt()
                    }
                    mMovieBookingDatabase?.cinemaDao()?.insertCinemaList(it)
                    onSuccess(it)
                },
                onFailure = onFailure,
            )
        }
    }

    override fun cinemaSeatingPlan(
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<SeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        token?.let { token ->
            mMovieBookingDataAgent.cinemaSeatingPlan(
                token = token.transformToBearerToken(),
                cinemaDayTimeslotId = cinemaDayTimeslotId,
                bookingDate = bookingDate,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun getSnackList(onSuccess: (List<SnackVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieBookingDatabase?.snackDao()?.selectAllSnack()?.let(onSuccess)
        token?.let { token ->
            mMovieBookingDataAgent.getSnackList(
                token = token.transformToBearerToken(),
                onSuccess = {
                    mMovieBookingDatabase?.snackDao()?.insertAllSnack(it)
                    onSuccess(it)
                },
                onFailure = onFailure
            )
        }
    }

    override fun getPaymentMethodList(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingDatabase?.paymentMethodDao()?.selectAllPaymentMethodList()?.let(onSuccess)
        token?.let { token ->
            mMovieBookingDataAgent.getPaymentMethodList(
                token = token.transformToBearerToken(),
                onSuccess = {
                    mMovieBookingDatabase?.paymentMethodDao()?.insertPaymentMethodList(it)
                    onSuccess(it)
                },
                onFailure = onFailure
            )
        }
    }

    override fun createCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        token?.let { token ->
            mMovieBookingDataAgent.createCard(
                token = token.transformToBearerToken(),
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                expirationDate = expirationDate,
                cvc = cvc,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }

    override fun checkout(
        checkoutRequest: CheckoutRequest,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        token?.let { token ->
            mMovieBookingDataAgent.checkout(
                token = token.transformToBearerToken(),
                checkoutRequest = checkoutRequest,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }


    private fun String.transformToBearerToken(): String = "Bearer $this"

}
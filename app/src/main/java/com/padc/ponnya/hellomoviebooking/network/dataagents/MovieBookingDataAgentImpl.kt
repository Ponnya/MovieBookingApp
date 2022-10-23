package com.padc.ponnya.hellomoviebooking.network.dataagents

import android.content.Context
import com.padc.ponnya.hellomoviebooking.data.vos.*
import com.padc.ponnya.hellomoviebooking.network.MovieBookingApi
import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import com.padc.ponnya.hellomoviebooking.network.responses.*
import com.padc.ponnya.hellomoviebooking.persistence.MovieBookingDatabase
import com.padc.ponnya.hellomoviebooking.utils.BASE_URL_MOVIE_BOOKING
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieBookingDataAgentImpl : MovieBookingDataAgent {

    private var mMovieBookingApi: MovieBookingApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIE_BOOKING)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mMovieBookingApi = retrofit.create(MovieBookingApi::class.java)
    }

    override fun registerWithEmail(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (RegisterAndLoginWithEmailResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password
        )?.enqueue(object : Callback<RegisterAndLoginWithEmailResponse> {
            override fun onResponse(
                call: Call<RegisterAndLoginWithEmailResponse>,
                response: Response<RegisterAndLoginWithEmailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<RegisterAndLoginWithEmailResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (RegisterAndLoginWithEmailResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.loginWithEmail(email = email, password = password)
            ?.enqueue(object : Callback<RegisterAndLoginWithEmailResponse> {
                override fun onResponse(
                    call: Call<RegisterAndLoginWithEmailResponse>,
                    response: Response<RegisterAndLoginWithEmailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(
                    call: Call<RegisterAndLoginWithEmailResponse>,
                    t: Throwable
                ) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun profile(
        token: String,
        onSuccess: (UserInfoVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.profile(token)
            ?.enqueue(object : Callback<RegisterAndLoginWithEmailResponse> {
                override fun onResponse(
                    call: Call<RegisterAndLoginWithEmailResponse>,
                    response: Response<RegisterAndLoginWithEmailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(
                    call: Call<RegisterAndLoginWithEmailResponse>,
                    t: Throwable
                ) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun logout(
        token: String,
        onSuccess: (LogoutResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.logout(token)?.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getCinemaDayTimeslot(
        token: String,
        movieId: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.getCinemaDayTimeSlot(
            token = token,
            movieId = movieId,
            date = date
        )?.enqueue(object : Callback<GetCinemaDayTimeslotResponse> {
            override fun onResponse(
                call: Call<GetCinemaDayTimeslotResponse>,
                response: Response<GetCinemaDayTimeslotResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { onSuccess(it) }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<GetCinemaDayTimeslotResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun cinemaSeatingPlan(
        token: String,
        cinemaDayTimeslotId: String,
        bookingDate: String,
        onSuccess: (List<SeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.cinemaSeatingPlan(
            token = token,
            cinemaDayTimeslotId = cinemaDayTimeslotId,
            bookingDate = bookingDate
        )?.enqueue(object : Callback<CinemaSeatingPlanResponse> {
            override fun onResponse(
                call: Call<CinemaSeatingPlanResponse>,
                response: Response<CinemaSeatingPlanResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        onSuccess(it.flatten())
                    }
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<CinemaSeatingPlanResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.getSnackList(token)?.enqueue(object : Callback<GetSnackListResponse> {
            override fun onResponse(
                call: Call<GetSnackListResponse>,
                response: Response<GetSnackListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let(onSuccess)
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<GetSnackListResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getPaymentMethodList(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.getPaymentMethodList(token)
            ?.enqueue(object : Callback<GetPaymentMethodListResponse> {
                override fun onResponse(
                    call: Call<GetPaymentMethodListResponse>,
                    response: Response<GetPaymentMethodListResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let(onSuccess)
                    } else {
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(call: Call<GetPaymentMethodListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun createCard(
        token: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: String,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.createCard(
            token = token,
            cardNumber = cardNumber,
            cardHolder = cardHolder,
            expirationDate = expirationDate,
            cvc = cvc,
        )?.enqueue(object : Callback<CreateCardResponse> {
            override fun onResponse(
                call: Call<CreateCardResponse>,
                response: Response<CreateCardResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let(onSuccess)
                } else {
                    onFailure(response.message() ?: "")
                }
            }

            override fun onFailure(call: Call<CreateCardResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun checkout(
        token: String,
        checkoutRequest: CheckoutRequest,
        onSuccess: (CheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieBookingApi?.checkout(token, checkoutRequest)
            ?.enqueue(object : Callback<CheckoutResponse> {
                override fun onResponse(
                    call: Call<CheckoutResponse>,
                    response: Response<CheckoutResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let(onSuccess)
                    } else {
                        onFailure(response.message() ?: "")
                    }
                }

                override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

}
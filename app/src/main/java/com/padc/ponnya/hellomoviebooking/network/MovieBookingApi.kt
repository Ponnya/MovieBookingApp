package com.padc.ponnya.hellomoviebooking.network

import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import com.padc.ponnya.hellomoviebooking.network.responses.*
import com.padc.ponnya.hellomoviebooking.utils.*
import retrofit2.Call
import retrofit2.http.*

interface MovieBookingApi {

    @POST(API_REGISTER_WITH_EMAIL)
    @FormUrlEncoded
    fun registerWithEmail(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PHONE) phone: String,
        @Field(PARAM_PASSWORD) password: String,
    ): Call<RegisterAndLoginWithEmailResponse>

    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun loginWithEmail(
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String,
    ): Call<RegisterAndLoginWithEmailResponse>

    @GET(API_PROFILE)
    fun profile(
        @Header(PARAM_AUTHORIZATION) token: String,
    ): Call<RegisterAndLoginWithEmailResponse>

    @POST(API_LOGOUT)
    fun logout(
        @Header(PARAM_AUTHORIZATION) token: String,
    ): Call<LogoutResponse>

    @GET(API_GET_CINEMA_DAY_TIME_SLOT)
    fun getCinemaDayTimeSlot(
        @Header(PARAM_AUTHORIZATION) token: String,
        @Query(PARAM_MOVIE_ID) movieId: String,
        @Query(PARAM_DATE) date: String
    ): Call<GetCinemaDayTimeslotResponse>

    @GET(API_CINEMA_SEATING_PLAN)
    fun cinemaSeatingPlan(
        @Header(PARAM_AUTHORIZATION) token: String,
        @Query(PARAM_CINEMA_DAY_TIMESLOT_ID) cinemaDayTimeslotId: String,
        @Query(PARAM_BOOKING_DATE) bookingDate: String,
    ): Call<CinemaSeatingPlanResponse>

    @GET(API_GET_SNACK_LIST)
    fun getSnackList(
        @Header(PARAM_AUTHORIZATION) token: String,
    ): Call<GetSnackListResponse>

    @GET(API_GET_PAYMENT_METHOD_LIST)
    fun getPaymentMethodList(
        @Header(PARAM_AUTHORIZATION) token: String,
    ): Call<GetPaymentMethodListResponse>

    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun createCard(
        @Header(PARAM_AUTHORIZATION) token: String,
        @Field(PARAM_CARD_NUMBER) cardNumber: String,
        @Field(PARAM_CARD_HOLDER) cardHolder: String,
        @Field(PARAM_EXPIRATION_DATE) expirationDate: String,
        @Field(PARAM_CVC) cvc: String,
    ): Call<CreateCardResponse>

    @POST(API_CHECKOUT)
    fun checkout(
        @Header(PARAM_AUTHORIZATION) token: String,
        @Body checkoutRequest: CheckoutRequest,
    ):Call<CheckoutResponse>
}
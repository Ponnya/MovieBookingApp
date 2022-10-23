package com.padc.ponnya.hellomoviebooking.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.adapters.VisaCardAdapter
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.CardVO
import com.padc.ponnya.hellomoviebooking.data.vos.CinemaVO
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO
import com.padc.ponnya.hellomoviebooking.network.requests.CheckoutRequest
import kotlinx.android.synthetic.main.activity_visa_card.*
import kotlin.math.abs
@RequiresApi(Build.VERSION_CODES.O)
class VisaCardActivity : AppCompatActivity() {
    lateinit var mVisaCardAdapter: VisaCardAdapter

    private var mMovieId: Int = 0
    private var mMovieName: String? = null
    private var mMovieRuntime: Int = 0
    private var mMovieImage: String? = null
    private var mCinemaVO: CinemaVO? = null
    private var mBookingDate: String? = null
    private var mSeatList: List<SeatVO> = listOf()
    private var mSnackList: List<SnackVO> = listOf()
    private var totalAmount: Int? = null

    private var mCardList: List<CardVO>? = null

    private var mCheckoutRequest: CheckoutRequest? = null

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var errorPass: Boolean = false

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        private const val EXTRA_CINEMA_VO = "EXTRA_CINEMA_VO"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_SEAT_LIST = "EXTRA_SEAT_LIST"
        private const val EXTRA_SNACK_LIST = "EXTRA_SNACK_LIST"
        private const val EXTRA_TOTAL_AMOUNT = "EXTRA_TOTAL_AMOUNT"

        fun newIntent(
            context: Context,
            movieId: Int,
            movieName: String,
            movieRuntime: Int,
            movieImage: String,
            cinemaVO: String,
            bookingDate: String,
            seatList: String,
            snackList: String,
            totalAmount: Int,
        ): Intent {
            return Intent(context, VisaCardActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
                .putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
                .putExtra(EXTRA_MOVIE_IMAGE, movieImage)
                .putExtra(EXTRA_CINEMA_VO, cinemaVO)
                .putExtra(EXTRA_BOOKING_DATE, bookingDate)
                .putExtra(EXTRA_SEAT_LIST, seatList)
                .putExtra(EXTRA_SNACK_LIST, snackList)
                .putExtra(EXTRA_TOTAL_AMOUNT, totalAmount)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa_card)

        setUpVisaCardViewPager2()
        setUpButtonClickListener()
        getExtraData()
        requestData()
        bindData()
    }

    private fun getExtraData() {
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        mMovieRuntime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieImage = intent.getStringExtra(EXTRA_MOVIE_IMAGE)
        mCinemaVO = Gson().fromJson(intent.getStringExtra(EXTRA_CINEMA_VO), CinemaVO::class.java)
        mBookingDate = intent.getStringExtra(EXTRA_BOOKING_DATE)
        val typeTokenSeatList = object : TypeToken<List<SeatVO>>() {}.type
        mSeatList = Gson().fromJson(intent.getStringExtra(EXTRA_SEAT_LIST), typeTokenSeatList)
        println(intent.getStringExtra(EXTRA_SNACK_LIST))
        val typeTokenSnackList = object : TypeToken<List<SnackVO>>() {}.type
        mSnackList = Gson().fromJson(intent.getStringExtra(EXTRA_SNACK_LIST), typeTokenSnackList)
        totalAmount = intent.getIntExtra(EXTRA_TOTAL_AMOUNT, 0)

    }

    private fun requestData() {
        mMovieBookingModel.profile(
            onSuccess = {
                mCardList = it.cards
                it.cards?.let { cards -> mVisaCardAdapter.setNewData(cards) }
            },
            onFailure = {
                setError(it)
            }
        )
    }

    private fun setError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_SHORT).show()
    }

    private fun bindData() {
        tvPaymentAmount.text = "$ $totalAmount.00"
    }

    private fun setUpVisaCardViewPager2() {
        mVisaCardAdapter = VisaCardAdapter()
        viewPagerVisaCard.adapter = mVisaCardAdapter
        viewPagerVisaCard.clipToPadding = false
        viewPagerVisaCard.clipChildren = false
        viewPagerVisaCard.offscreenPageLimit = 3
        viewPagerVisaCard.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerVisaCard.setPageTransformer(compositePageTransformer)

    }


    private fun setUpButtonClickListener() {
        btnAddNewCard.setOnClickListener {
            resultLauncher.launch(NewCardActivity.newIntent(this))
        }


        btnBackVisaCard.setOnClickListener {
            finish()
        }

        btnConfirmVisaCard.setOnClickListener {
            val position = viewPagerVisaCard.currentItem
            mCardList?.let { cardList ->
                prepareCheckoutRequestData(cardList[position].id ?: 0)
                if (errorPass) {
                    mCheckoutRequest?.let { checkoutRequest ->
                        mMovieBookingModel.checkout(
                            checkoutRequest = checkoutRequest,
                            onSuccess = {
                                startActivity(
                                    FinishedPaymentActivity.newIntent(
                                        context = this,
                                        checkout = Gson().toJson(it),
                                        movieRuntime = mMovieRuntime,
                                        movieName = mMovieName ?: "",
                                        cinemaName = mCinemaVO?.cinema ?: "",
                                        movieImage = mMovieImage ?: "",
                                    )
                                )
                            },
                            onFailure = {
                                setError(it)
                            }

                        )
                    }
                }

            }

        }
    }

    private fun prepareCheckoutRequestData(cardId: Int) {

        mCinemaVO?.let { cinemaVO ->
            if (mSeatList.isNotEmpty()) {
                mBookingDate?.let { bookingDate ->
                    totalAmount?.let { amount ->
                        mCheckoutRequest = CheckoutRequest(
                            cinemaDayTimeslotId = cinemaVO.timeslots?.get(0)?.cinemaDayTimeslotId,
                            row = mSeatList.distinctBy { it.symbol }
                                .joinToString(",") { "${it.symbol}" },
                            seatNumber = mSeatList.joinToString(",") { "${it.seatName}" },
                            bookingDate = bookingDate,
                            totalPrice = amount,
                            movieId = mMovieId,
                            cardId = cardId,
                            cinemaId = cinemaVO.cinemaId,
                            snacks = mSnackList
                        )
                        errorPass = true
                    }

                }
            }
        }

    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                val typeToken = object : TypeToken<List<CardVO>>() {}.type

                data?.getStringExtra(NewCardActivity.EXTRA_CARD_LIST)?.let {
                    mCardList = Gson().fromJson(it, typeToken)
                    mCardList?.let { cardList ->
                        mVisaCardAdapter.setNewData(cardList.reversed())
                        viewPagerVisaCard.setCurrentItem(0, true)
                    }
                }

            }
        }
}
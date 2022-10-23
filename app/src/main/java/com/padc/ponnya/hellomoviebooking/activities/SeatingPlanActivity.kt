package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.adapters.MovieSeatAdapter
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.CinemaVO
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO
import com.padc.ponnya.hellomoviebooking.delegate.SeatingPlanDelegate
import kotlinx.android.synthetic.main.activity_seating_plan.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SeatingPlanActivity : AppCompatActivity(), SeatingPlanDelegate {
    lateinit var mMovieSeatAdapter: MovieSeatAdapter

    private var mMovieId: Int = 0
    private var mMovieName: String? = null
    private var mMovieRuntime: Int = 0
    private var mMovieImage: String? = null
    private var mCinemaVO: CinemaVO? = null
    private var mBookingDate: String? = null
    private var mSeatList: List<SeatVO> = listOf()
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        private const val EXTRA_CINEMA_VO = "EXTRA_CINEMA_VO"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"


        fun newIntent(
            context: Context,
            movieId: Int,
            movieName: String,
            movieRuntime: Int,
            movieImage: String,
            cinemaVO: String,
            bookingDate: String

        ): Intent {
            return Intent(context, SeatingPlanActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
                .putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
                .putExtra(EXTRA_MOVIE_IMAGE, movieImage)
                .putExtra(EXTRA_CINEMA_VO, cinemaVO)
                .putExtra(EXTRA_BOOKING_DATE, bookingDate)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)


        setUpButtonClickListener()
        setUpSeatingPlanRecyclerView(14)
        getExtraData()
        bindData()

        requestData()

    }

    private fun requestData() {
        if (mBookingDate != null && mCinemaVO != null) {
            mMovieBookingModel.cinemaSeatingPlan(
                cinemaDayTimeslotId = mCinemaVO!!.timeslots?.get(0)?.cinemaDayTimeslotId.toString(),
                bookingDate = mBookingDate!!,
                onSuccess = {

                    mSeatList = it
                    mMovieSeatAdapter.setNewData(it)
                },
                onFailure = {
                    showError(it)
                }
            )
        }
    }

    private fun showError(errorMsg: String) {
        Snackbar.make(window.decorView, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    private fun bindData() {
        tvMovieNameSeatingPlan.text = mMovieName ?: ""
        tvCinemaSeatingPlan.text = mCinemaVO?.cinema ?: ""
        tvDateAndTimeSeatingPlan.text =
            (mBookingDate?.toFormatDate() + mCinemaVO?.timeslots?.get(0)?.startTime) ?: ""
    }

    private fun getExtraData() {
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        mMovieRuntime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieImage = intent.getStringExtra(EXTRA_MOVIE_IMAGE)
        mCinemaVO = Gson().fromJson(intent.getStringExtra(EXTRA_CINEMA_VO), CinemaVO::class.java)
        mBookingDate = intent.getStringExtra(EXTRA_BOOKING_DATE)
    }

    private fun setUpSeatingPlanRecyclerView(seatCount: Int) {
        mMovieSeatAdapter = MovieSeatAdapter(this)
        recyclerViewSeatingPlan.adapter = mMovieSeatAdapter
        recyclerViewSeatingPlan.layoutManager = GridLayoutManager(applicationContext, seatCount)
    }

    private fun setUpButtonClickListener() {
        //back button
        btnBackSeatingPlan.setOnClickListener {
            finish()
        }

        //confirm button
        btnConfirmSeatingPlan.setOnClickListener {
            mSeatList.filter { it.isSelected }.let {
                if (it.isNotEmpty()) {
                    mMovieName?.let { movieName ->
                        mCinemaVO?.let { cinemaVO ->
                            mBookingDate?.let { bookingDate ->
                                startActivity(
                                    ComboSetActivity.newIntent(
                                        context = this,
                                        movieId = mMovieId,
                                        movieName = movieName,
                                        movieRuntime = mMovieRuntime,
                                        movieImage = mMovieImage?:"",
                                        cinemaVO = Gson().toJson(cinemaVO),
                                        bookingDate = bookingDate,
                                        seatList = Gson().toJson(it)
                                    )
                                )
                            } ?: run {
                                startActivity(HomeActivity.newIntent(this))
                            }
                        } ?: run {
                            startActivity(HomeActivity.newIntent(this))
                        }
                    } ?: run {
                        startActivity(HomeActivity.newIntent(this))
                    }
                } else {
                    Snackbar.make(window.decorView, "Please select seat", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }

    }


    private fun String.toFormatDate(): String =
        LocalDate.parse(this).format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, "))

    override fun onTapSeat(seatId: Int, symbol: String) {
        mSeatList.find { seatId == it.id && symbol == it.symbol }?.let {
            it.isSelected = !it.isSelected
        }
        tvTicketSeatingPlan.text = mSeatList.filter { it.isSelected }.size.toString()
        tvSeatsSeatingPlan.text = mSeatList.filter { it.isSelected }.joinToString(", ") {
            "${it.seatName}"
        }
        btnConfirmSeatingPlan.text = getString(
            R.string.btn_buy_ticket_lbl,
            mSeatList.filter { it.isSelected }.sumOf { it.price ?: 0.0 })
        mMovieSeatAdapter.setNewData(mSeatList)
    }

}
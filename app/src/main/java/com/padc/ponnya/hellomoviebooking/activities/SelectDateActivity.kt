package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.adapters.SelectDateAdapter
import com.padc.ponnya.hellomoviebooking.adapters.SelectTimeAdapter
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.CinemaVO
import com.padc.ponnya.hellomoviebooking.delegate.SelectDateDelegate
import com.padc.ponnya.hellomoviebooking.delegate.SelectTimeDelegate
import kotlinx.android.synthetic.main.activity_select_date.*
import java.time.LocalDate

class SelectDateActivity : AppCompatActivity(), SelectDateDelegate, SelectTimeDelegate {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var mCinemaList: List<CinemaVO>? = null
    private var mMovieId: Int = 0
    private var mMovieName: String? = null
    private var mMovieRuntime: Int = 0
    private var mMovieImage: String? = null
    private var mCinemaVO: CinemaVO? = null
    private var mBookingDate: String? = LocalDate.now().toString()

    private lateinit var mSelectDateAdapter: SelectDateAdapter
    private lateinit var mSelectTimeAdapter: SelectTimeAdapter

    private lateinit var mDateList: List<Pair<LocalDate, Boolean>>

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        fun newIntent(
            context: Context,
            movieId: Int,
            movieName: String,
            movieRuntime: Int,
            movieImage: String
        ): Intent {
            return Intent(context, SelectDateActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
                .putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
                .putExtra(EXTRA_MOVIE_IMAGE, movieImage)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)
        setUpDateViewholder()
        setUpTimeViewholder()
        setUpButtonClickListener()

        setUpDate()
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        mMovieRuntime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieImage = intent.getStringExtra(EXTRA_MOVIE_IMAGE)
        requestData(mMovieId, LocalDate.now())


    }

    private fun requestData(movieId: Int, date: LocalDate) {
        mMovieBookingModel.getCinemaDayTimeslot(
            movieId = movieId.toString(),
            date = date.toString(),
            onSuccess = {
                mCinemaList = it
                mSelectTimeAdapter.setNewData(it)
            },
            onFailure = {
                showError(it)
            }

        )
    }

    private fun showError(errorMsg: String) {
        Snackbar.make(window.decorView, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setUpDateViewholder() {
        mSelectDateAdapter = SelectDateAdapter(this)
        recyclerViewDateSelect.adapter = mSelectDateAdapter
        recyclerViewDateSelect.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpTimeViewholder() {
        mSelectTimeAdapter = SelectTimeAdapter(this, this)
        recyclerViewTimeSelect.adapter = mSelectTimeAdapter
        recyclerViewTimeSelect.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpButtonClickListener() {


        btnNext.setOnClickListener {
            mBookingDate?.let { bookingDate ->
                mCinemaVO?.let { cinemaVO ->
                    startActivity(
                        SeatingPlanActivity.newIntent(
                            context = this,
                            movieId = mMovieId,
                            movieName = mMovieName ?: "",
                            movieRuntime = mMovieRuntime,
                            movieImage = mMovieImage?:"",
                            cinemaVO = Gson().toJson(cinemaVO),
                            bookingDate = bookingDate
                        )
                    )
                } ?: run {
                    Snackbar.make(
                        window.decorView,
                        "Please choose booking time",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            } ?: run {
                Snackbar.make(
                    window.decorView,
                    "Please choose booking date",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }

        btnBackSelectDate.setOnClickListener {
            finish()
        }

    }

    private fun setUpDate() {
        val date = LocalDate.now()
        var list = mutableListOf<Pair<LocalDate, Boolean>>()
        for (i in 0..13) {
            list += if (i == 0) {
                Pair(date.plusDays(i.toLong()), true)
            } else {
                Pair(date.plusDays(i.toLong()), false)
            }
        }
        mDateList = list
        mSelectDateAdapter.setNewData(mDateList)
    }

    // to fix select date
    override fun onTapDate(bookingDate: LocalDate) {
        val list = mDateList.map {
            it.copy(
                second = it.first == bookingDate

            )
        }

        requestData(mMovieId, bookingDate)
        mDateList = list
        mBookingDate = bookingDate.toString()
        mCinemaVO = null
        mSelectDateAdapter.setNewData(mDateList)
    }

    override fun onTapTime(cinemaDayTimeslotId: Int) {

        mCinemaList?.let {
            val cinemaList = it.map { cinemaVO ->
                cinemaVO.copy(
                    timeslots = cinemaVO.timeslots?.map { timeslot1 ->
                        val isSelected =
                            timeslot1.cinemaDayTimeslotId == cinemaDayTimeslotId
                        if (isSelected) {
                            mCinemaVO = cinemaVO.copy(timeslots = listOf(timeslot1))
                        }
                        timeslot1.copy(isSelected = isSelected)
                    }
                )
            }

            mSelectTimeAdapter.setNewData(cinemaList)
        }
    }
}
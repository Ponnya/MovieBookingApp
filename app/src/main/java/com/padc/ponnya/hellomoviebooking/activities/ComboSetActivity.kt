package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.adapters.ComboSetAdapter
import com.padc.ponnya.hellomoviebooking.adapters.PaymentMethodAdapter
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.PaymentMethodVO
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO
import com.padc.ponnya.hellomoviebooking.delegate.PaymentMethodDelegate
import com.padc.ponnya.hellomoviebooking.delegate.SnackButtonDelegate
import kotlinx.android.synthetic.main.activity_combo_set.*

class ComboSetActivity : AppCompatActivity(), SnackButtonDelegate, PaymentMethodDelegate {
    lateinit var mComboSetAdapter: ComboSetAdapter
    lateinit var mPaymentMethodAdapter: PaymentMethodAdapter

    private var mMovieId: Int = 0
    private var mMovieName: String? = null
    private var mMovieRuntime: Int = 0
    private var mCinemaVO: String? = null
    private var mBookingDate: String? = null
    private var mMovieImage: String? = null
    private var mSeatList: List<SeatVO> = listOf()
    private var mSnackList: List<SnackVO> = listOf()
    private var mPaymentMethodList: List<PaymentMethodVO> = listOf()

    private var totalAmount: Double = 0.00

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        private const val EXTRA_CINEMA_VO = "EXTRA_CINEMA_VO"
        private const val EXTRA_BOOKING_DATE = "EXTRA_BOOKING_DATE"
        private const val EXTRA_SEAT_LIST = "EXTRA_SEAT_LIST"

        fun newIntent(
            context: Context,
            movieId: Int,
            movieName: String,
            movieRuntime: Int,
            movieImage: String,
            cinemaVO: String,
            bookingDate: String,
            seatList: String,

            ): Intent {
            return Intent(context, ComboSetActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
                .putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
                .putExtra(EXTRA_MOVIE_IMAGE, movieImage)
                .putExtra(EXTRA_CINEMA_VO, cinemaVO)
                .putExtra(EXTRA_BOOKING_DATE, bookingDate)
                .putExtra(EXTRA_SEAT_LIST, seatList)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo_set)

        setUpComboSetRecyclerView()
        setUpPaymentMethodRecyclerView()
        setUpButtonClickListener()
        setUpHint(edtPromoCode, tvPromoCode, getString(R.string.lbl_enter_promo_code))
        getExtraData()
        totalAmount = mSeatList.sumOf { it.price ?: 0.0 }
        bindData()
        requestData()
    }

    private fun getExtraData() {
        mMovieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        mMovieRuntime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieImage = intent.getStringExtra(EXTRA_MOVIE_IMAGE)
        mCinemaVO = intent.getStringExtra(EXTRA_CINEMA_VO)
        mBookingDate = intent.getStringExtra(EXTRA_BOOKING_DATE)

        val typeToken = object : TypeToken<List<SeatVO>>() {}.type
        mSeatList = Gson().fromJson(intent.getStringExtra(EXTRA_SEAT_LIST), typeToken)


    }

    private fun bindData() {
        tvTotalAmount.text = getString(R.string.total_amount_lbl, totalAmount)
        btnPayTotalAmount.text = getString(R.string.btn_pay_total_amount_lbl, totalAmount)
    }

    private fun requestData() {
        //Get Snack List
        mMovieBookingModel.getSnackList(
            onSuccess = {
                mSnackList = it
                mComboSetAdapter.setNewData(it)
            },
            onFailure = {
                setError(it)
            }
        )

        //Get Payment Method List
        mMovieBookingModel.getPaymentMethodList(
            onSuccess = {
                mPaymentMethodList = it
                mPaymentMethodAdapter.setNewData(it)
            },
            onFailure = {
                setError(it)
            }
        )
    }

    private fun setError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_SHORT).show()
    }

    private fun setUpComboSetRecyclerView() {
        mComboSetAdapter = ComboSetAdapter(this)
        recyclerViewComboSet.adapter = mComboSetAdapter
        recyclerViewComboSet.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpPaymentMethodRecyclerView() {
        mPaymentMethodAdapter = PaymentMethodAdapter(this)
        recyclerViewPaymentMethod.adapter = mPaymentMethodAdapter
        recyclerViewPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpButtonClickListener() {
        //Pay Total Amount Button
        btnPayTotalAmount.setOnClickListener {
            if (mSeatList.isNotEmpty()) {
                mMovieName?.let { movieName ->
                    mCinemaVO?.let { cinemaVO ->
                        mBookingDate?.let { bookingDate ->
                            startActivity(
                                VisaCardActivity.newIntent(
                                    context = this,
                                    movieId = mMovieId,
                                    movieName = movieName,
                                    movieRuntime = mMovieRuntime,
                                    movieImage = mMovieImage?:"",
                                    cinemaVO = cinemaVO,
                                    bookingDate = bookingDate,
                                    seatList = Gson().toJson(mSeatList),
                                    snackList = Gson().toJson(mSnackList.filter { it.quantity > 0 }),
                                    totalAmount = totalAmount.toInt()
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

        //Back Button
        btnBackComboSet.setOnClickListener {
            finish()
        }
    }

    private fun setUpHint(editText: EditText, textView: TextView, hint: String) {
        editText.doAfterTextChanged {
            if (editText.length() != 0) {
                textView.visibility = View.VISIBLE
                editText.hint = ""
            } else {
                textView.visibility = View.GONE
                editText.hint = hint
            }
        }
    }

    private fun calculateTotalAmount() {
        totalAmount =
            mSeatList.sumOf { it.price ?: 0.0 } + mSnackList.filter { it.quantity > 0 }.sumOf {
                (it.price?.times(it.quantity)) ?: 0
            }
    }

    override fun onTapPlusButton(snackId: Int) {
        mSnackList.find { it.id == snackId }?.let { ++it.quantity }

        calculateTotalAmount()
        bindData()
        mComboSetAdapter.setNewData(mSnackList)
    }

    override fun onTapMinusButton(snackId: Int) {
        mSnackList.find { it.id == snackId }?.let {
            if (it.quantity > 0)
                --it.quantity
        }
        calculateTotalAmount()
        bindData()
        mComboSetAdapter.setNewData(mSnackList)
    }

    override fun onTapPayment(paymentId: Int) {
        val list = mPaymentMethodList.map {
            it.copy(
                isSelected = it.id == paymentId
            )
        }
        mPaymentMethodAdapter.setNewData(list)
    }

}
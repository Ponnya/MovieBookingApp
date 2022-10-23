package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.CheckoutVO
import com.padc.ponnya.hellomoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_finished_payment.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class FinishedPaymentActivity : AppCompatActivity() {
    private var mMovieName: String? = null
    private var mMovieRuntime: Int = 0
    private var mCheckout: CheckoutVO? = null
    private var mCinemaName: String? = null
    private var mMovieImage: String? = null

    companion object {
        private const val EXTRA_CHECKOUT_DATA = "EXTRA_CHECKOUT_DATA"
        private const val EXTRA_MOVIE_RUNTIME = "EXTRA_MOVIE_RUNTIME"
        private const val EXTRA_MOVIE_Name = "EXTRA_MOVIE_Name"
        private const val EXTRA_CINEMA_Name = "EXTRA_CINEMA_Name"
        private const val EXTRA_MOVIE_IMAGE = "EXTRA_MOVIE_IMAGE"
        fun newIntent(
            context: Context,
            checkout: String,
            movieRuntime: Int,
            movieName: String,
            cinemaName: String,
            movieImage: String,
        ): Intent {
            return Intent(
                context,
                FinishedPaymentActivity::class.java
            ).putExtra(EXTRA_CHECKOUT_DATA, checkout)
                .putExtra(EXTRA_MOVIE_RUNTIME, movieRuntime)
                .putExtra(EXTRA_MOVIE_Name, movieName)
                .putExtra(EXTRA_CINEMA_Name, cinemaName)
                .putExtra(EXTRA_MOVIE_IMAGE, movieImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finished_payment)

        setUpButtonClickListener()

        mCheckout =
            Gson().fromJson(intent.getStringExtra(EXTRA_CHECKOUT_DATA), CheckoutVO::class.java)
        mCinemaName = intent.getStringExtra(EXTRA_CINEMA_Name)
        mMovieName = intent.getStringExtra(EXTRA_MOVIE_Name)
        mMovieRuntime = intent.getIntExtra(EXTRA_MOVIE_RUNTIME, 0)
        mMovieImage = intent.getStringExtra(EXTRA_MOVIE_IMAGE)

        bindData()
    }
    private fun bindData(){
        Glide.with(this)
            .load("$IMAGE_BASE_URL${mMovieImage}")
            .into(ivMovieTicket)
        tvTicketMovieName.text = mMovieName
        tvTicketMovieRuntime.text = "${mMovieRuntime}m"
        tvBookingNo.text = mCheckout?.bookingNo
        tvShowTime.text = mCheckout?.timeslot?.startTime +" - "+mCheckout?.bookingDate?.toFormatDate()
        tvTheater.text = mCinemaName
        tvRow.text = mCheckout?.row
        tvSeat.text = mCheckout?.seat
        tvPrice.text = mCheckout?.total

        ivBarcode.setImageBitmap(
            createBarcodeBitmap(
                barcodeValue = mCheckout?.bookingNo?:"",
                barcodeColor = getColor(R.color.black),
                backgroundColor = getColor(android.R.color.white),
                widthPixels = 250,
                heightPixels = 60
            )
        )

    }

    private fun String.toFormatDate(): String =
        LocalDate.parse(this).format(DateTimeFormatter.ofPattern("dd MMMM"))

    private fun setUpButtonClickListener() {
        btnClose.setOnClickListener {
            startActivity(HomeActivity.newIntent(this))
        }
    }
    private fun createBarcodeBitmap(
        barcodeValue: String,
        @ColorInt barcodeColor: Int,
        @ColorInt backgroundColor: Int,
        widthPixels: Int,
        heightPixels: Int
    ): Bitmap {
        val bitMatrix = Code128Writer().encode(
            barcodeValue,
            BarcodeFormat.CODE_128,
            widthPixels,
            heightPixels
        )

        val pixels = IntArray(bitMatrix.width * bitMatrix.height)
        for (y in 0 until bitMatrix.height) {
            val offset = y * bitMatrix.width
            for (x in 0 until bitMatrix.width) {
                pixels[offset + x] =
                    if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
            }
        }

        val bitmap = Bitmap.createBitmap(
            bitMatrix.width,
            bitMatrix.height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.setPixels(
            pixels,
            0,
            bitMatrix.width,
            0,
            0,
            bitMatrix.width,
            bitMatrix.height
        )
        return bitmap
    }
}
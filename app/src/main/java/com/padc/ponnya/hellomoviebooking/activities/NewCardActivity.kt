package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_new_card.*

class NewCardActivity : AppCompatActivity() {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        const val EXTRA_CARD_LIST = "EXTRA_CARD_LIST"
        fun newIntent(context: Context): Intent {
            return Intent(context, NewCardActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        setUpEdtHint()
        setUpButtonClickListener()
        setUpExpirationEditText()

    }


    private fun setUpEdtHint() {
        setUpHint(edtCardNumber, tvCardNumber, getString(R.string.lbl_card_number))
        setUpHint(edtCardHolder, tvCardHolder, getString(R.string.lbl_card_holder))
        setUpHint(
            edtExpireDateNewCard,
            tvExpireDateNewCard,
            getString(R.string.lbl_expiration_date)
        )
        setUpHint(edtCVC, tvCVC, getString(R.string.lbl_cvc))
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

    private fun setUpButtonClickListener() {
        btnBackNewCard.setOnClickListener {
            finish()
        }
        btnConfirmNewCard.setOnClickListener {
            setUpButtonConfirmClickListener()
        }
    }

    private fun setUpExpirationEditText() {
        edtExpireDateNewCard.filters = arrayOf<InputFilter>(filterExpirationDate())
    }

    private fun setUpButtonConfirmClickListener() {
        if (errorPassCheck(edtCardNumber, "card number") &&
            errorPassCheck(edtCardHolder, "card holder") &&
            errorPassCheck(edtExpireDateNewCard, "expiration date") &&
            errorPassCheck(edtCVC, "cvc")
        ) {
            mMovieBookingModel.createCard(
                cardNumber = edtCardNumber.text.toString(),
                cardHolder = edtCardHolder.text.toString(),
                expirationDate = edtExpireDateNewCard.text.toString(),
                cvc = edtCVC.text.toString(),
                onSuccess = {
                    val intent = intent.putExtra(EXTRA_CARD_LIST, Gson().toJson(it))
                    setResult(RESULT_OK, intent)
                    finish()
                },
                onFailure = {
                    setError(it)
                }
            )
        }

    }

    private fun setError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_SHORT).show()
    }

    private fun errorPassCheck(edtText: EditText, name: String): Boolean {
        val data = edtText.text.toString()
        if (data == "") {
            edtText.error = getString(R.string.error_msg_empty, String)
            return false
        }
        return true
    }

    inner class filterExpirationDate : InputFilter {

        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dStart: Int,
            dEnd: Int
        ): CharSequence? {
            try {
                if (source?.isNotEmpty()!! && source?.isNotBlank()!! && source != "/") {
                    if (dest?.length!! < 5) {
                        if (dest.length == 2) {
                            return "/${source}"
                        }
                        return null
                    }
                }

            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }
    }

}
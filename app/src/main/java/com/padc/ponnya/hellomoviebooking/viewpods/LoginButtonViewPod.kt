package com.padc.ponnya.hellomoviebooking.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.delegate.LoginButtonDelegate
import com.padc.ponnya.hellomoviebooking.fragments.LoginFragment
import com.padc.ponnya.hellomoviebooking.fragments.SignInFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.viewpod_login_button.view.*

class LoginButtonViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var mLoginButtonDelegate: LoginButtonDelegate
    private lateinit var mFragment: Fragment
    private var nameErrorPass = true
    private var emailErrorPass = true
    private var phoneErrorPass = true
    private var passwordErrorPass = true
    private var passwordLoginErrorPass = true

    override fun onFinishInflate() {


        btnConfirm.setOnClickListener {
            if (mFragment is SignInFragment) {
                mLoginButtonDelegate.onTapSignInButton(
                    name = nameErrorCheck(mFragment.edtSignInName),
                    email = emailErrorCheck(mFragment.edtSignInEmail),
                    phone = phoneErrorCheck(mFragment.edtSignInPhoneNo),
                    password = passwordErrorCheck(mFragment.edtSignInPassword),
                    errorCheck = {
                        var error = false
                        it.first?.let { code ->
                            when (code) {
                                201 -> error = true
                                400 -> showErrorMsg(it.second ?: "")
                            }
                        }
                        error
                    },
                    errorPass = nameErrorPass && emailErrorPass && phoneErrorPass && passwordErrorPass
                )
            } else if (mFragment is LoginFragment) {
                mLoginButtonDelegate.onTapLoginInButton(
                    email = emailErrorCheck(mFragment.edtLoginEmail),
                    password = passwordLoginErrorCheck(mFragment.edtLoginPassword),
                    errorCheck = {
                        var error = false
                        it.first?.let { code ->
                            when (code) {
                                200 -> error = true
                                400 -> showErrorMsg(it.second ?: "")
                            }
                        }

                        error
                    },
                    errorPass = emailErrorPass && passwordLoginErrorPass
                )
            }
        }
        super.onFinishInflate()
    }

    fun setDelegate(
        delegate: LoginButtonDelegate,
        fragment: Fragment
    ) {
        mFragment = fragment
        this.mLoginButtonDelegate = delegate
    }

    private fun showErrorMsg(errorMsg: String) {
        tvErrorMsg.visibility = VISIBLE
        tvErrorMsg.text = errorMsg
    }

    private fun nameErrorCheck(edtName: EditText): String {
        val name = edtName.text.toString()
        nameErrorPass = true
        if (name == "") {
            edtName.error = context.getString(R.string.error_msg_empty, "name")
            nameErrorPass = false
        }

        return name
    }

    private fun phoneErrorCheck(edtPhone: EditText): String {
        val regexStr = Regex("^[0-9]{10,13}$")
        val phone = edtPhone.text.toString()
        phoneErrorPass = true
        if (phone == "" || !phone.matches(regexStr)) {
            edtPhone.error = context.getString(R.string.error_msg_empty, "valid phone number")
            phoneErrorPass = false
        }

        return phone
    }

    private fun emailErrorCheck(edtEmail: EditText): String {
        val regexStr = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")
        val email = edtEmail.text.toString()
        emailErrorPass = true
        if (email == "" || !email.matches(regexStr)) {
            edtEmail.error = context.getString(R.string.error_msg_empty, "valid email")
            emailErrorPass = false
        }

        return email
    }

    private fun passwordErrorCheck(edtPassword: EditText): String {
        val password = edtPassword.text.toString()
        passwordErrorPass = true
        if (password == "") {
            edtPassword.error = context.getString(R.string.error_msg_empty, "password")
            passwordErrorPass = false
        } else if (password.length < 6) {
            edtPassword.error = context.getString(R.string.error_msg_invalid_password)
            passwordErrorPass = false
        }

        return password
    }

    private fun passwordLoginErrorCheck(edtPassword: EditText): String {
        val password = edtPassword.text.toString()
        passwordLoginErrorPass = true
        if (password == "") {
            edtPassword.error = context.getString(R.string.error_msg_empty, "password")
            passwordLoginErrorPass = false
        }

        return password
    }

}
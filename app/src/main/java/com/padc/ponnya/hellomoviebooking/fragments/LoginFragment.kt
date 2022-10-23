package com.padc.ponnya.hellomoviebooking.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.delegate.LoginButtonDelegate
import com.padc.ponnya.hellomoviebooking.viewpods.LoginButtonViewPod
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    lateinit var mDelegate: LoginButtonDelegate
    private lateinit var loginButtonViewPod: LoginButtonViewPod

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEdtHint()
        setUpViewPodWithDelegate()

    }

    override fun onAttach(context: Context) {
        mDelegate = context as LoginButtonDelegate
        super.onAttach(context)
    }

    private fun setUpEdtHint() {
        setUpHint(edtLoginEmail, tvEmail, getString(R.string.lbl_email))
        setUpHint(edtLoginPassword, tvPassword, getString(R.string.lbl_password))
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

    private fun setUpViewPodWithDelegate() {
        loginButtonViewPod = viewPodLoginButton as LoginButtonViewPod

            loginButtonViewPod.setDelegate(
                delegate = mDelegate,
                fragment =  this
            )

    }
}


package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.delegate.LoginButtonDelegate
import com.padc.ponnya.hellomoviebooking.fragments.LoginFragment
import com.padc.ponnya.hellomoviebooking.fragments.SignInFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginButtonDelegate {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        hideStatusBar()
        setUpTabLayout()
        setUpTabBarClickListiner()


    }

    private fun showError(errorMsg: String) {
        Snackbar.make(window.decorView, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    private fun hideStatusBar() {
        // Hide the status bar.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, loginMainContainer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setUpTabLayout() {
        tabLayoutLogin.newTab().apply {
            text = getString(R.string.lbl_login)
            tabLayoutLogin.addTab(this)
        }
        tabLayoutLogin.newTab().apply {
            text = getString(R.string.lbl_sign_in)
            tabLayoutLogin.addTab(this)
        }
    }

    private fun setUpTabBarClickListiner() {
        supportFragmentManager.commit {
            add<LoginFragment>(R.id.fragmentContainerLogin)
        }

        tabLayoutLogin.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    getString(R.string.lbl_login) -> {
                        supportFragmentManager.commit {
                            replace<LoginFragment>(R.id.fragmentContainerLogin)
                        }
                    }
                    getString(R.string.lbl_sign_in) -> {

                        supportFragmentManager.commit {
                            replace<SignInFragment>(R.id.fragmentContainerLogin)
                        }
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    override fun onTapLoginInButton(
        email: String,
        password: String,
        errorCheck: (Pair<Int?, String?>) -> Boolean,
        errorPass: Boolean
    ) {

        if (errorPass) {
            mMovieBookingModel.loginWithEmail(
                email = email,
                password = password,
                onSuccess = {

                    if (errorCheck(it)) {
                        startActivity(HomeActivity.newIntent(this))
                        finish()
                    }
                },
                onFailure = {
                    showError(it)
                }
            )
        }
    }

    override fun onTapSignInButton(
        name: String,
        email: String,
        phone: String,
        password: String,
        errorCheck: (Pair<Int?, String?>) -> Boolean,
        errorPass: Boolean
    ) {
        if (errorPass) {
            mMovieBookingModel.registerWithEmail(
                name = name,
                email = email,
                phone = phone,
                password = password,
                onSuccess = {
                    if (errorCheck(it)) {
                        startActivity(HomeActivity.newIntent(this))
                        finish()
                    }
                },
                onFailure = {
                    showError(it)
                }
            )
        }
    }
}
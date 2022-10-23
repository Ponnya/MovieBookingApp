package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.models.MovieModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.UserInfoVO
import com.padc.ponnya.hellomoviebooking.delegate.MovieViewholderDelegate
import com.padc.ponnya.hellomoviebooking.viewpods.MovieListsViewPod
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.drawer_custom_layout.*

class HomeActivity : AppCompatActivity(), MovieViewholderDelegate {
    lateinit var mNowShowingMovieListsViewPod: MovieListsViewPod
    lateinit var mComingSoonMovieListsViewPod: MovieListsViewPod
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpMovieViewPod()
        setUpDrawer()
        setUpListener()

        requestData()
    }

    private fun requestData() {
        //profile
        mMovieBookingModel.profile(
            onSuccess = {
                setNewData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        //Now Playing Movies
        mMovieModel.nowPlayingMovies(
            onSuccess = {
                mNowShowingMovieListsViewPod.setData(it)
            },
            onFailure = {
                showError(it)
            }
        )

        //Coming Soon Movies
        mMovieModel.comingSoonMovies(
            onSuccess = {
                mComingSoonMovieListsViewPod.setData(it)
            },
            onFailure = {
                showError(it)
            }
        )
    }


    private fun setNewData(userInfoVO: UserInfoVO) {
        tvProfileName.text = getString(R.string.user_name, userInfoVO.name ?: "")
        tvProfileNameDrawer.text = userInfoVO.name ?: ""
        tvEmailDrawer.text = userInfoVO.email ?: ""
    }

    private fun showError(errorMsg: String) {
        Snackbar.make(window.decorView, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setUpMovieViewPod() {
        mNowShowingMovieListsViewPod = viewPodNowShowing as MovieListsViewPod
        mNowShowingMovieListsViewPod.setUpMovieListViewPod(this)

        mComingSoonMovieListsViewPod = viewPodComingSoon as MovieListsViewPod
        mComingSoonMovieListsViewPod.setUpMovieListViewPod(this)
        mComingSoonMovieListsViewPod.setUpTitle(getString(R.string.lbl_coming_soon))
    }

    private fun setUpDrawer() {
        setSupportActionBar(toolBarHome)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayoutHome,
            R.string.open,
            R.string.close
        )
        actionBarDrawerToggle.let {
            drawerLayoutHome.addDrawerListener(it)
            it.syncState()
        }
        toolBarHome.elevation = 0.0f
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    private fun setUpListener() {
        btnLogOut.setOnClickListener {
            drawerLayoutHome.closeDrawer(GravityCompat.START)
            mMovieBookingModel.logout(
                onSuccess = {
                    if (it.code == 200) {
                        startActivity(LoginActivity.newIntent(this))
                        finish()
                        true
                    }else {
                        showError(it.message ?: "")
                        false
                    }

                },
                onFailure = {
                    showError(it)
                }
            )

        }
    }

    override fun onBackPressed() {
        if (drawerLayoutHome.isDrawerOpen(GravityCompat.START))
            drawerLayoutHome.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onTapMovieList(movieId: Int) {
        startActivity(DetailActivity.newIntent(this,movieId))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
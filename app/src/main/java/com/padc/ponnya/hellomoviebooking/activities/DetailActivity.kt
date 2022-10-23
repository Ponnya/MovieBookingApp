package com.padc.ponnya.hellomoviebooking.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.adapters.CastAdapter
import com.padc.ponnya.hellomoviebooking.adapters.GenreAdapter
import com.padc.ponnya.hellomoviebooking.data.models.MovieModel
import com.padc.ponnya.hellomoviebooking.data.models.MovieModelImpl
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var mCastAdapter: CastAdapter
    lateinit var mGenreAdapter: GenreAdapter
    private var movieId: Int? = null
    private var mMovieName: String? = null
    private var mRuntime: Int? = null
    private var mMovieImage: String? =null

    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            return Intent(context, DetailActivity::class.java).putExtra(
                EXTRA_MOVIE_ID,
                movieId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setUpCastRecyclerView()
        setUpButtonClickListener()

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let { movieId ->
            mMovieModel.movieDetail(
                movieId = movieId.toString(),
                onSuccess = {
                    mRuntime = it.runtime
                    mMovieImage = it.posterPath
                    setNewData(it)
                },
                onFailure = {
                    showError(it)
                }
            )
            mMovieModel.movieCredit(
                movieId = movieId.toString(),
                onSuccess = {
                    mCastAdapter.setNewData(it)
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

    private fun setNewData(movieVO: MovieVO) {
        Glide.with(this)
            .load("${IMAGE_BASE_URL}${movieVO.posterPath}")
            .into(ivMoviePoster)
        tvDetailMovieName.text = movieVO.title ?: ""
        tvDetailMovieDuration.text = movieVO.getRuntimeWithHour()
        ratingBarMovie.rating = movieVO.getRatingBasedOnFiveStars()
        tvIMDBRating.text = "IMDB${movieVO.voteAverage ?: ""}"
        movieVO.genres?.let { mGenreAdapter.setNewData(it) }
        tvMovieSummary.text = movieVO.overview ?: ""

        mMovieName = movieVO.title


    }

    private fun setUpCastRecyclerView() {
        mCastAdapter = CastAdapter()
        recyclerViewCast.adapter = mCastAdapter
        recyclerViewCast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mGenreAdapter = GenreAdapter()
        recyclerViewGenre.adapter = mGenreAdapter
        recyclerViewGenre.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpButtonClickListener() {
        btnGetYourTicket.setOnClickListener {
            movieId?.let {
                startActivity(
                    SelectDateActivity.newIntent(
                        this,
                        it,
                        mMovieName ?: "",
                        mRuntime ?: 0,
                        mMovieImage?:""
                    )
                )
            }
        }
        btnBackDetail.setOnClickListener {
            finish()
        }
    }

}
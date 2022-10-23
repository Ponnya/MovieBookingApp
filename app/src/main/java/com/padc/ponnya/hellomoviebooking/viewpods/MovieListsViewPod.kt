package com.padc.ponnya.hellomoviebooking.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.ponnya.hellomoviebooking.adapters.MovieListsAdapter
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.delegate.MovieViewholderDelegate
import kotlinx.android.synthetic.main.viewpod_movie_lists.view.*

class MovieListsViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: MovieViewholderDelegate
    lateinit var mMovieListsAdapter: MovieListsAdapter

    fun setUpTitle(title: String) {
        tvTitleOfMovieLists.text = title
    }

    fun setUpMovieListViewPod(delegate: MovieViewholderDelegate) {
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    fun setData(movieList: List<MovieVO>) {
        mMovieListsAdapter.setNewData(movieList)

    }


    private fun setDelegate(delegate: MovieViewholderDelegate) {
        this.mDelegate = delegate
    }

    private fun setUpMovieRecyclerView() {
        mMovieListsAdapter = MovieListsAdapter(mDelegate)
        recyclerViewMovieLists.adapter = mMovieListsAdapter
        recyclerViewMovieLists.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}
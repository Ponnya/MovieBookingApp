package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.delegate.MovieViewholderDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.MovieListsViewholder

class MovieListsAdapter(val mDelegate: MovieViewholderDelegate) :
    RecyclerView.Adapter<MovieListsViewholder>() {

    private var mMovieList: List<MovieVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListsViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_movie_lists, parent, false)
        return MovieListsViewholder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieListsViewholder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int = mMovieList.size

    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }


}
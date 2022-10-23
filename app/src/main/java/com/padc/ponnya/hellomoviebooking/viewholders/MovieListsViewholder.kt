package com.padc.ponnya.hellomoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO
import com.padc.ponnya.hellomoviebooking.delegate.MovieViewholderDelegate
import com.padc.ponnya.hellomoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_movie_lists.view.*

class MovieListsViewholder(itemView: View, private val mDelegate: MovieViewholderDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { mDelegate.onTapMovieList(it.id) }
        }
    }

    fun bindData(movieVO: MovieVO?) {
        mMovieVO = movieVO
        Glide.with(itemView.context)
            .load(IMAGE_BASE_URL + movieVO?.posterPath)
            .into(itemView.ivMovie)
        itemView.tvMovieName.text = movieVO?.title

    }

}
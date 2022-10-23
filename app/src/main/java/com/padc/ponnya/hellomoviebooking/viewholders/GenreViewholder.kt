package com.padc.ponnya.hellomoviebooking.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderGenreBinding

class GenreViewholder(private val viewholderGenreBinding: ViewholderGenreBinding) :
    RecyclerView.ViewHolder(viewholderGenreBinding.root) {
    fun bindData(genreType: String) {
        viewholderGenreBinding.chipGenre.text = genreType
    }
}
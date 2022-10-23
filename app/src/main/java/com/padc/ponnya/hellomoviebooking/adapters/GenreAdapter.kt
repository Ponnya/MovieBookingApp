package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.GenreVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderGenreBinding
import com.padc.ponnya.hellomoviebooking.viewholders.GenreViewholder

class GenreAdapter : RecyclerView.Adapter<GenreViewholder>() {
    private var mGenreList: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewholder {
        val view =
            ViewholderGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewholder(view)
    }

    override fun onBindViewHolder(holder: GenreViewholder, position: Int) {
        if (mGenreList.isNotEmpty()) {
            holder.bindData(mGenreList[position].name ?: "")
        }
    }

    override fun getItemCount(): Int = mGenreList.size

    fun setNewData(genreList: List<GenreVO>) {
        mGenreList = genreList
        notifyDataSetChanged()

    }
}
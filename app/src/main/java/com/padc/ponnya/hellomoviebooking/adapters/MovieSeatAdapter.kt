package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderMovieSeatBinding
import com.padc.ponnya.hellomoviebooking.delegate.SeatingPlanDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.MovieSeatViewholder

class MovieSeatAdapter(val mDelegate: SeatingPlanDelegate) :
    RecyclerView.Adapter<MovieSeatViewholder>() {
    private var mMovieSeat: List<SeatVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewholder {
        val view =
            ViewholderMovieSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieSeatViewholder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewholder, position: Int) {
        if (mMovieSeat.isNotEmpty()) {
            holder.dataBind(mMovieSeat[position])
        }
    }

    override fun getItemCount(): Int = mMovieSeat.size

    fun setNewData(movieSeats: List<SeatVO>) {
        this.mMovieSeat = movieSeats
        notifyDataSetChanged()
    }
}
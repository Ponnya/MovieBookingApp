package com.padc.ponnya.hellomoviebooking.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.SeatVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderMovieSeatBinding
import com.padc.ponnya.hellomoviebooking.delegate.SeatingPlanDelegate

class MovieSeatViewholder(
    private val viewholderMovieSeatBinding: ViewholderMovieSeatBinding,
    private val mDelegate: SeatingPlanDelegate
) :
    RecyclerView.ViewHolder(viewholderMovieSeatBinding.root) {
    private var seatVO: SeatVO? = null

    private var seatIdList: List<Pair<Int, String>>? = null

    init {
        viewholderMovieSeatBinding.root.setOnClickListener {
            seatVO?.let {
                if (it.isMovieSeatAvailable()) {
                    println(it.seatName)
                    it.symbol?.let { symbol -> mDelegate.onTapSeat(it.id, symbol) }
                }
            }
        }
    }

    fun dataBind(data: SeatVO) {
        seatVO = data
        when {
            data.isMovieSeatAvailable() -> {
                viewholderMovieSeatBinding.tvMovieSeatTitle.visibility = View.GONE
                viewholderMovieSeatBinding.root.background = ContextCompat.getDrawable(
                    viewholderMovieSeatBinding.root.context,
                    R.drawable.background_movie_seat_available
                )
                if (data.isSelected) {
                    viewholderMovieSeatBinding.tvMovieSeatTitle.visibility = View.VISIBLE
                    viewholderMovieSeatBinding.tvMovieSeatTitle.text = data.seatName
                    viewholderMovieSeatBinding.tvMovieSeatTitle.setTextColor(
                        ContextCompat.getColor(
                            viewholderMovieSeatBinding.root.context,
                            R.color.white
                        )
                    )
                    viewholderMovieSeatBinding.tvMovieSeatTitle.textSize = 10.0f
                    viewholderMovieSeatBinding.root.background = ContextCompat.getDrawable(
                        viewholderMovieSeatBinding.root.context,
                        R.drawable.background_movie_selected
                    )
                }
            }
            data.isMovieSeatTaken() -> {
                viewholderMovieSeatBinding.tvMovieSeatTitle.visibility = View.GONE
                viewholderMovieSeatBinding.root.background = ContextCompat.getDrawable(
                    viewholderMovieSeatBinding.root.context,
                    R.drawable.background_movie_seat_taken
                )

            }
            data.isMovieSeatRowTitle() -> {
                viewholderMovieSeatBinding.tvMovieSeatTitle.visibility = View.VISIBLE
                viewholderMovieSeatBinding.tvMovieSeatTitle.text = data.symbol
                viewholderMovieSeatBinding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        viewholderMovieSeatBinding.root.context,
                        R.color.white
                    )
                )

            }
            else -> {
                viewholderMovieSeatBinding.tvMovieSeatTitle.visibility = View.GONE
                viewholderMovieSeatBinding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        viewholderMovieSeatBinding.root.context,
                        R.color.white
                    )
                )

            }
        }
    }
}
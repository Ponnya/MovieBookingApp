package com.padc.ponnya.hellomoviebooking.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.CinemaVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderSelectTimeBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectTimeDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.SelectTimeViewholder

class SelectTimeAdapter(private val context: Context, val mDelegate: SelectTimeDelegate) :
    RecyclerView.Adapter<SelectTimeViewholder>() {
    private var mCinemaList: List<CinemaVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeViewholder {
        val view =
            ViewholderSelectTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectTimeViewholder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: SelectTimeViewholder, position: Int) {
        if (mCinemaList.isNotEmpty()) {
            holder.dataBind(
                label = mCinemaList[position].cinema ?: "",
                timeList = mCinemaList[position].timeslots ?: listOf(),
                context = context
            )
        }
    }

    override fun getItemCount(): Int = mCinemaList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }
}
package com.padc.ponnya.hellomoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderSelectDateBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectDateDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.SelectDateViewholder
import java.time.LocalDate

class SelectDateAdapter(val delegate: SelectDateDelegate) :
    RecyclerView.Adapter<SelectDateViewholder>() {

    private var mDateList: List<Pair<LocalDate, Boolean>> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDateViewholder {
        val view =
            ViewholderSelectDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectDateViewholder(view, delegate)
    }

    override fun onBindViewHolder(holder: SelectDateViewholder, position: Int) {
        if (mDateList.isNotEmpty()) {
            holder.dataBind(mDateList[position])
        }

    }


    override fun getItemCount(): Int = mDateList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(dateList: List<Pair<LocalDate, Boolean>>) {
        mDateList = dateList
        notifyDataSetChanged()
    }
}
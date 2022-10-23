package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderTimeBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectTimeDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.TimeViewholder

class TimeAdapter(private val item: List<TimeslotVO>,private val delegate: SelectTimeDelegate) : RecyclerView.Adapter<TimeViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewholder {
        val view = ViewholderTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeViewholder(view,delegate)
    }

    override fun onBindViewHolder(holder: TimeViewholder, position: Int) {

        if (item.isNotEmpty()) {
            holder.dataBind(
                item[position]
            )
        }
        /* else{
             holder.dataBind(
                 time = words[0]
             )
         }*/
    }

    override fun getItemCount(): Int = item.size
}
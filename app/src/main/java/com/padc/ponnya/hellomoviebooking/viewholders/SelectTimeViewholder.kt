package com.padc.ponnya.hellomoviebooking.viewholders

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.adapters.TimeAdapter
import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderSelectTimeBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectTimeDelegate

class SelectTimeViewholder(
    private val viewholderSelectTimeBinding: ViewholderSelectTimeBinding,
    private val delegate: SelectTimeDelegate
) :
    RecyclerView.ViewHolder(viewholderSelectTimeBinding.root) {
    fun dataBind(label: String, timeList: List<TimeslotVO>, context: Context) {
        viewholderSelectTimeBinding.tvSelectTimeLabel.text = label
        viewholderSelectTimeBinding.recyclerViewTime.adapter = TimeAdapter(timeList,delegate)
        viewholderSelectTimeBinding.recyclerViewTime.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }

}
package com.padc.ponnya.hellomoviebooking.viewholders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderTimeBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectTimeDelegate

class TimeViewholder(
    private val viewholderTimeBinding: ViewholderTimeBinding,
    private val delegate: SelectTimeDelegate
) :
    RecyclerView.ViewHolder(viewholderTimeBinding.root) {

    private var mTimeSlot: TimeslotVO? = null

    init {
        viewholderTimeBinding.root.setOnClickListener {
            mTimeSlot?.let { it.cinemaDayTimeslotId?.let { it1 -> delegate.onTapTime(it1) } }
        }
    }

    fun dataBind(timeslot: TimeslotVO) {
        mTimeSlot = timeslot
        val words = timeslot.startTime?.split("\\s".toRegex())?.toTypedArray() ?: arrayOf()
        viewholderTimeBinding.tvTime.text = words[0]
        viewholderTimeBinding.tvAMPM.text = words[1]

        if (timeslot.isSelected == true) {
            viewholderTimeBinding.tvTime.setTextColor(
                ContextCompat.getColor(
                    viewholderTimeBinding.root.context,
                    R.color.white
                )
            );
            viewholderTimeBinding.tvAMPM.setTextColor(
                ContextCompat.getColor(
                    viewholderTimeBinding.root.context,
                    R.color.white
                )
            );
            viewholderTimeBinding.relativeLayoutTime.backgroundTintList =
                viewholderTimeBinding.root.context.getColorStateList(R.color.colorPrimary)
        }
    }
    /*   fun dataBind(time: String){
           viewholderTimeBinding.tvTime.text = time
           val layoutParams = viewholderTimeBinding.tvTime.layoutParams as RelativeLayout.LayoutParams;
           layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
           viewholderTimeBinding.tvTime.layoutParams = layoutParams;

           viewholderTimeBinding.tvAMPM.text = ""
       }*/
}
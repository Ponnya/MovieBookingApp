package com.padc.ponnya.hellomoviebooking.viewholders

import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderSelectDateBinding
import com.padc.ponnya.hellomoviebooking.delegate.SelectDateDelegate
import java.time.LocalDate

class SelectDateViewholder(
    private val viewholderSelectDateBinding:
    ViewholderSelectDateBinding, private val delegate: SelectDateDelegate
) : RecyclerView.ViewHolder(viewholderSelectDateBinding.root) {

    private var mLocalDate: LocalDate? = null

    init {
        viewholderSelectDateBinding.root.setOnClickListener {
            mLocalDate?.let {
                delegate.onTapDate(it)
            }
        }

    }

    fun dataBind(date: Pair<LocalDate, Boolean>) {
        mLocalDate = date.first
        viewholderSelectDateBinding.tvSelectDay.text = (date.first.dayOfWeek).toString().take(2)
        viewholderSelectDateBinding.tvSelectDate.text = (date.first.dayOfMonth).toString()

        if (date.second){
            viewholderSelectDateBinding.tvSelectDay.setTextColor(ContextCompat.getColor(viewholderSelectDateBinding.root.context, R.color.white));
            viewholderSelectDateBinding.tvSelectDay.setTypeface(null, Typeface.BOLD)

            viewholderSelectDateBinding.tvSelectDate.setTextColor(ContextCompat.getColor(viewholderSelectDateBinding.root.context, R.color.white));
            viewholderSelectDateBinding.tvSelectDate.setTypeface(null, Typeface.BOLD)
        }
        else{
            viewholderSelectDateBinding.tvSelectDay.setTextColor(ContextCompat.getColor(viewholderSelectDateBinding.root.context, R.color.secondaryTextColor));
            viewholderSelectDateBinding.tvSelectDay.setTypeface(null, Typeface.NORMAL)

            viewholderSelectDateBinding.tvSelectDate.setTextColor(ContextCompat.getColor(viewholderSelectDateBinding.root.context, R.color.secondaryTextColor));
            viewholderSelectDateBinding.tvSelectDate.setTypeface(null, Typeface.NORMAL)
        }
    }


}
package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.CardVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderVisaCardBinding
import com.padc.ponnya.hellomoviebooking.dummy.dummyVisaCard
import com.padc.ponnya.hellomoviebooking.viewholders.VisaCardViewholder

class VisaCardAdapter : RecyclerView.Adapter<VisaCardViewholder>() {
    private var mCardList: List<CardVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisaCardViewholder {
        val view =
            ViewholderVisaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisaCardViewholder(view)
    }

    override fun onBindViewHolder(holder: VisaCardViewholder, position: Int) {
        if(mCardList.isNotEmpty()) {
            holder.dataBind(mCardList[position])
        }
    }

    override fun getItemCount(): Int = mCardList.size

    fun setNewData(cardList: List<CardVO>){
        mCardList = cardList
        notifyDataSetChanged()
    }
}
package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderComboSetBinding
import com.padc.ponnya.hellomoviebooking.delegate.SnackButtonDelegate
import com.padc.ponnya.hellomoviebooking.dummy.dummyComboSet
import com.padc.ponnya.hellomoviebooking.viewholders.ComboSetViewholder

class ComboSetAdapter(private val mDelegate: SnackButtonDelegate) : RecyclerView.Adapter<ComboSetViewholder>() {
    private var mSnackList: List<SnackVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSetViewholder {
        val view =
            ViewholderComboSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComboSetViewholder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ComboSetViewholder, position: Int) {
        if(mSnackList.isNotEmpty()) {
            holder.dataBind(mSnackList[position])
        }
    }

    override fun getItemCount(): Int = mSnackList.size

    fun setNewData(snackList: List<SnackVO>){
        mSnackList = snackList
        notifyDataSetChanged()
    }
}
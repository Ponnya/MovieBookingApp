package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO
import com.padc.ponnya.hellomoviebooking.viewholders.CastViewholder

class CastAdapter : RecyclerView.Adapter<CastViewholder>() {
    private var mActorList: List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cast, parent, false)
        return CastViewholder(view)
    }

    override fun onBindViewHolder(holder: CastViewholder, position: Int) {
        if (mActorList.isNotEmpty()) {
            holder.bindData(mActorList[position])
        }
    }

    override fun getItemCount(): Int = mActorList.size

    fun setNewData(actorList: List<ActorVO>) {
        mActorList = actorList
        notifyDataSetChanged()
    }
}
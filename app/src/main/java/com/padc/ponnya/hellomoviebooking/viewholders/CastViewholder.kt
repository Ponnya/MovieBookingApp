package com.padc.ponnya.hellomoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO
import com.padc.ponnya.hellomoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_cast.view.*

class CastViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actorVO: ActorVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actorVO.profilePath}")
            .into(itemView.ivCast)
    }
}
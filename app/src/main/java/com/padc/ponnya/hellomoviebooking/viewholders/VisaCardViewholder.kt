package com.padc.ponnya.hellomoviebooking.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.CardVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderVisaCardBinding

class VisaCardViewholder(private val viewholderVisaCardBinding: ViewholderVisaCardBinding) :
    RecyclerView.ViewHolder(viewholderVisaCardBinding.root) {
        fun dataBind(card: CardVO){
            viewholderVisaCardBinding.tvVisaCardNumber.text =  card.cardNumber?.takeLast(4)
            viewholderVisaCardBinding.tvCardHolderName.text = card.cardHolder
            viewholderVisaCardBinding.tvExpireDate.text = card.expirationDate
        }
}
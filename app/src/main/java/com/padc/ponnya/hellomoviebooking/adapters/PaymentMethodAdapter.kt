package com.padc.ponnya.hellomoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.PaymentMethodVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderPaymentMethodBinding
import com.padc.ponnya.hellomoviebooking.delegate.PaymentMethodDelegate
import com.padc.ponnya.hellomoviebooking.viewholders.PaymentMethodViewholder

class PaymentMethodAdapter(private val mDelegate: PaymentMethodDelegate) :
    RecyclerView.Adapter<PaymentMethodViewholder>() {
    private var mPaymentMethodList: List<PaymentMethodVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewholder {
        val view = ViewholderPaymentMethodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentMethodViewholder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewholder, position: Int) {
        if (mPaymentMethodList.isNotEmpty()) {
            holder.dataBind(mPaymentMethodList[position])
        }
    }

    override fun getItemCount(): Int = mPaymentMethodList.size

    fun setNewData(paymentMethodList: List<PaymentMethodVO>) {
        mPaymentMethodList = paymentMethodList
        notifyDataSetChanged()
    }
}
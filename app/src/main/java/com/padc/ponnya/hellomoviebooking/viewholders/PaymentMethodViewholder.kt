package com.padc.ponnya.hellomoviebooking.viewholders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.R
import com.padc.ponnya.hellomoviebooking.data.vos.PaymentMethodVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderPaymentMethodBinding
import com.padc.ponnya.hellomoviebooking.delegate.PaymentMethodDelegate

class PaymentMethodViewholder(
    private val viewholderPaymentMethodBinding: ViewholderPaymentMethodBinding,
    private val mDelegate: PaymentMethodDelegate
) :
    RecyclerView.ViewHolder(viewholderPaymentMethodBinding.root) {

    private var mPaymentMethodVO: PaymentMethodVO? = null

    init {
        viewholderPaymentMethodBinding.root.setOnClickListener {
            mPaymentMethodVO?.id?.let {
                mDelegate.onTapPayment(it)
            }
        }
    }

    fun dataBind(paymentMethodVO: PaymentMethodVO) {
        mPaymentMethodVO = paymentMethodVO
        viewholderPaymentMethodBinding.tvTypeOfPaymentMethod.text = paymentMethodVO.name ?: ""
        viewholderPaymentMethodBinding.tvPaymentMethodDetail.text =
            paymentMethodVO.description ?: ""

        if (paymentMethodVO.isSelected) {
            viewholderPaymentMethodBinding.tvTypeOfPaymentMethod.setTextColor(
                ContextCompat.getColor(
                    viewholderPaymentMethodBinding.root.context,
                    R.color.colorPrimary
                )
            )

            viewholderPaymentMethodBinding.tvPaymentMethodDetail.setTextColor(
                ContextCompat.getColor(
                    viewholderPaymentMethodBinding.root.context,
                    R.color.colorPrimary
                )
            )
        } else {
            viewholderPaymentMethodBinding.tvTypeOfPaymentMethod.setTextColor(
                ContextCompat.getColor(
                    viewholderPaymentMethodBinding.root.context,
                    R.color.black
                )
            )

            viewholderPaymentMethodBinding.tvPaymentMethodDetail.setTextColor(
                ContextCompat.getColor(
                    viewholderPaymentMethodBinding.root.context,
                    R.color.black
                )
            )

        }
    }
}
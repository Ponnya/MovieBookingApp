package com.padc.ponnya.hellomoviebooking.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO
import com.padc.ponnya.hellomoviebooking.databinding.ViewholderComboSetBinding
import com.padc.ponnya.hellomoviebooking.delegate.SnackButtonDelegate

class ComboSetViewholder(private val viewholderComboSetBinding: ViewholderComboSetBinding, private val mDelegate: SnackButtonDelegate) :
    RecyclerView.ViewHolder(viewholderComboSetBinding.root) {
    private var mSnackVO: SnackVO? = null
    init {
        viewholderComboSetBinding.btnPlus.setOnClickListener{
            mSnackVO?.id?.let {  mDelegate.onTapPlusButton(it) }
        }
        viewholderComboSetBinding.btnMinus.setOnClickListener{
            mSnackVO?.id?.let {  mDelegate.onTapMinusButton(it) }
        }
    }
    fun dataBind(snackVO: SnackVO) {
        mSnackVO = snackVO
        viewholderComboSetBinding.tvComboSetType.text = snackVO.name?:""
        viewholderComboSetBinding.tvComboSetDetail.text = snackVO.description?:""
        viewholderComboSetBinding.tvAmount.text = "${snackVO.price}$"

            viewholderComboSetBinding.tvCount.text = snackVO.quantity.toString()

    }
}
package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.PaymentMethodVO

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethodList(paymentMethodList: List<PaymentMethodVO>)

    @Query("SELECT * FROM payment_method")
    fun selectAllPaymentMethodList(): List<PaymentMethodVO>

    @Query("DELETE FROM payment_method")
    fun deleteAllPaymentMethodList()
}
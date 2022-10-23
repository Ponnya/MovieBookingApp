package com.padc.ponnya.hellomoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snack")
data class SnackVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: Int?,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String?,

    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,

    @SerializedName("total_price")
    @ColumnInfo(name = "total_price")
    val totalPrice: Int?,
)

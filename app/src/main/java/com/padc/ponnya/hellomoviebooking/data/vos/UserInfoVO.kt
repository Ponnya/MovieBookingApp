package com.padc.ponnya.hellomoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.persistence.typeconverters.CardsTypeConverter

@Entity(tableName = "user_info")
@TypeConverters(CardsTypeConverter::class)
data class UserInfoVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String?,

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    val phone: String?,

    @SerializedName("total_expense")
    @ColumnInfo(name = "total_expense")
    val totalExpense: Int?,

    @SerializedName("profile_image")
    @ColumnInfo(name = "profile_image")
    val profileImage: String?,

    @SerializedName("cards")
    @ColumnInfo(name = "cards")
    val cards: List<CardVO>?,

    @ColumnInfo(name = "token")
    val token: String?,
)
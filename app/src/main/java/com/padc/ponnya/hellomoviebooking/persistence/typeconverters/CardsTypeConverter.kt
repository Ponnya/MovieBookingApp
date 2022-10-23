package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.CardVO

class CardsTypeConverter {

    @TypeConverter
    fun toString(cardList: List<CardVO>?): String{
        return Gson().toJson(cardList)
    }

    @TypeConverter
    fun toCardList(cardListJsonString: String): List<CardVO>?{
        val cardListType = object : TypeToken<List<CardVO>?>(){}.type
        return Gson().fromJson(cardListJsonString,cardListType)
    }
}
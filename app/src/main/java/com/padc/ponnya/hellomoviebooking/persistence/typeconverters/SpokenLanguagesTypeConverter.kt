package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.SpokenLanguageVO

class SpokenLanguagesTypeConverter {

    @TypeConverter
    fun toString(spokenLanguageList: List<SpokenLanguageVO>?): String {
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageListJsonString: String): List<SpokenLanguageVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJsonString, spokenLanguageListType)
    }
}
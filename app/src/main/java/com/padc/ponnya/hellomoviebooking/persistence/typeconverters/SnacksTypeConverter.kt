package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO

class SnacksTypeConverter {
    @TypeConverter
    fun toString(snackList: List<SnackVO>?): String {
        return Gson().toJson(snackList)
    }

    @TypeConverter
    fun toSnackList(snackListJsonString: String): List<SnackVO>? {
        val snackListType = object : TypeToken<List<SnackVO>?>() {}.type
        return Gson().fromJson(snackListJsonString, snackListType)
    }
}
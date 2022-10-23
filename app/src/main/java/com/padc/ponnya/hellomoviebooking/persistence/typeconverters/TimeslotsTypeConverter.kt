package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO

class TimeslotsTypeConverter {

    @TypeConverter
    fun toString(timeslotList: List<TimeslotVO>?): String {
        return Gson().toJson(timeslotList)
    }

    @TypeConverter
    fun toTimeslotList(timeslotListJsonString: String): List<TimeslotVO>? {
        val timeslotListType = object : TypeToken<List<TimeslotVO>?>() {}.type
        return Gson().fromJson(timeslotListJsonString, timeslotListType)
    }
}
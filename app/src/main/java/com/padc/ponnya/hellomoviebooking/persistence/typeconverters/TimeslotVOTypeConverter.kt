package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.TimeslotVO

class TimeslotVOTypeConverter {

    @TypeConverter
    fun toString(timeslotVO: TimeslotVO?): String{
        return Gson().toJson(timeslotVO)
    }

    @TypeConverter
    fun toTimeslotVO(timeslotVOJsonString: String):TimeslotVO?{
        val timeslotVOType = object : TypeToken<TimeslotVO?>(){}.type
        return Gson().fromJson(timeslotVOJsonString,timeslotVOType)
    }
}
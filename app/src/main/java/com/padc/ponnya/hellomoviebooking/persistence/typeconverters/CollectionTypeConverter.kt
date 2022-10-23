package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.CollectionVO

class CollectionTypeConverter {

    @TypeConverter
    fun toString(collectorVO: CollectionVO?): String{
        return Gson().toJson(collectorVO)
    }

    @TypeConverter
    fun toCollectorVO(collectorVOJsonString: String): CollectionVO?{
        val collectorVOType = object : TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(collectorVOJsonString,collectorVOType)
    }
}
package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.ProductionCountryVO

class ProductionCountriesTypeConverter {

    @TypeConverter
    fun toString(productionCountryList: List<ProductionCountryVO>?): String {
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountryList(productionCountryListJsonString: String): List<ProductionCountryVO>? {
        val productionCountryListType = object : TypeToken<List<ProductionCountryVO>?>() {}.type
        return Gson().fromJson(productionCountryListJsonString, productionCountryListType)
    }
}
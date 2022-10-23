package com.padc.ponnya.hellomoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.ponnya.hellomoviebooking.data.vos.ProductionCompanyVO

class ProductionCompaniesTypeConverter {

    @TypeConverter
    fun toString(productionCompanyList: List<ProductionCompanyVO>?): String{
        return Gson().toJson(productionCompanyList)
    }

    @TypeConverter
    fun toProductionCompanyList(productionCompanyListJsonString: String) : List<ProductionCompanyVO>?{
        val productionCompanyListType = object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(productionCompanyListJsonString,productionCompanyListType)
    }
}
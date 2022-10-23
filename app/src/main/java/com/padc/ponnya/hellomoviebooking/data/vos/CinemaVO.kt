package com.padc.ponnya.hellomoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.ponnya.hellomoviebooking.persistence.typeconverters.TimeslotsTypeConverter

@Entity(tableName = "cinema", primaryKeys = ["date","movie_id","cinema_id"])
@TypeConverters(TimeslotsTypeConverter::class)
data class CinemaVO(
    @SerializedName("cinema_id")
    @ColumnInfo(name = "cinema_id")
    val cinemaId: Int = 0,

    @SerializedName("cinema")
    @ColumnInfo(name = "cinema")
    val cinema: String?,

    @SerializedName("timeslots")
    @ColumnInfo(name = "timeslots")
    val timeslots: List<TimeslotVO>?,

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "movie_id")
    var movieId: Int=0,
)

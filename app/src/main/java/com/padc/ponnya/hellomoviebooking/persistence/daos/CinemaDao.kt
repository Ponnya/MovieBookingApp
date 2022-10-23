package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.CinemaVO

@Dao
interface CinemaDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaList(cinemaList: List<CinemaVO>)

    @Query("SELECT * FROM cinema WHERE date = :date AND movie_id = :movieId")
    fun selectCinemaListByDate(date: String,movieId: Int): List<CinemaVO>

    @Query("DELETE FROM cinema")
    fun deleteAllCinema()
}
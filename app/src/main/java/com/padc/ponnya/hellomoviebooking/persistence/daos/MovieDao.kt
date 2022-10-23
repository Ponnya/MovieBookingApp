package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.MovieVO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieVO: MovieVO?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<MovieVO>)

    @Query("SELECT * FROM movies WHERE type = :type")
    fun selectMovieByType(type: String): List<MovieVO>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun selectMovieById(id: Int): MovieVO

    @Query("DELETE FROM movies")
    fun deleteMovie()
}
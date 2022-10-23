package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.ActorVO

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActorList(actorList: List<ActorVO>)

    @Query("SELECT * FROM actors WHERE movie_id = :movieId")
    fun selectActorsByMovieId(movieId: Int) : List<ActorVO>

    @Query("DELETE FROM actors")
    fun deleteActors()
}
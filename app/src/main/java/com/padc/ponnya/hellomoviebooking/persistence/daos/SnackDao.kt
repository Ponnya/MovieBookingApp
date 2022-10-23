package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.SnackVO

@Dao
interface SnackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSnack(snackList: List<SnackVO>)

    @Query("SELECT * FROM snack")
    fun selectAllSnack(): List<SnackVO>

    @Query("SELECT * FROM snack WHERE id = :id")
    fun selectSnackById(id: Int): SnackVO?

    @Query("DELETE FROM snack")
    fun deleteAllSnack()
}
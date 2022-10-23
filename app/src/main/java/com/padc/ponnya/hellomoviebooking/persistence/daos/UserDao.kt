package com.padc.ponnya.hellomoviebooking.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.ponnya.hellomoviebooking.data.vos.UserInfoVO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:UserInfoVO?)

    @Query("SELECT * FROM user_info")
    fun selectUser(): UserInfoVO?

    @Query("SELECT token FROM user_info")
    fun selectToken(): String?

    @Query("DELETE FROM user_info")
    fun deleteAllUser()
}
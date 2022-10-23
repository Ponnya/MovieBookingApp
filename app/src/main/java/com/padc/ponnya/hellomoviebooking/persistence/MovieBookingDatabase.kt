package com.padc.ponnya.hellomoviebooking.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padc.ponnya.hellomoviebooking.data.vos.*
import com.padc.ponnya.hellomoviebooking.persistence.daos.*

@Database(
    entities = [UserInfoVO::class, MovieVO::class, ActorVO::class, SnackVO::class, PaymentMethodVO::class, CinemaVO::class],
    version = 5,
    exportSchema = false
)
abstract class MovieBookingDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "THE_MOVIE_BOOKING_DB"
        var dbInstance: MovieBookingDatabase? = null

        fun getDBInstance(context: Context): MovieBookingDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(
                        context, MovieBookingDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun paymentMethodDao(): PaymentMethodDao
    abstract fun snackDao(): SnackDao
    abstract fun cinemaDao(): CinemaDao
}
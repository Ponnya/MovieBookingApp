package com.padc.ponnya.hellomoviebooking

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.padc.ponnya.hellomoviebooking.activities.HomeActivity
import com.padc.ponnya.hellomoviebooking.data.models.MovieBookingModelImpl
import com.padc.ponnya.hellomoviebooking.data.models.MovieModelImpl

class MovieBookingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MovieBookingModelImpl.initDatabase(applicationContext)
        MovieModelImpl.initDatabase(applicationContext)

        MovieBookingModelImpl.token?.let {
            startActivity(HomeActivity.newIntent(this))
        }
    }
}
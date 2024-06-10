package com.example.binaryminification

import android.app.Application
import androidx.room.Room
import com.example.binaryminification.data.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-db"
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}
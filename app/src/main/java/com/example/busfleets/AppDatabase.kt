package com.example.busfleets

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busfleets.DAOs.AppDAO
import com.example.busfleets.classes.Trip
import com.example.busfleets.classes.User

@Database(entities = [Trip::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getAppDAO(): AppDAO

    companion object{

        private const val DB_NAME = "bus_fleets.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(instance==null){
                instance = Room.databaseBuilder(context,
                    AppDatabase::class.java, DB_NAME).build()
            }
            return instance
        }
    }
}
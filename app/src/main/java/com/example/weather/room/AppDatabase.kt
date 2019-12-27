package com.example.weather.room

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import com.example.weather.room.dao.CityDao
import com.example.weather.room.entity.CityTable

@Database(entities = [(CityTable::class)], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {

        /**
         * The only instance
         */
        private var sInstance: AppDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }

}
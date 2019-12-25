package com.example.wather.room

import android.content.Context
import androidx.room.RoomDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters


//https://medium.com/mindorks/room-kotlin-android-architecture-components-71cad5a1bb35

//@Database(entities = arrayOf(CityTable::class), version = 1)
//@TypeConverters(DateTypeConverter::class)
//@Database(entities = [CityTable::class], version = 1)
//@Database(entities = arrayOf(Test::class), version = 1)
//@Database(entities = [(Test::class)], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun cityDao(): CityDao
//
//    companion object {
//        var INSTANCE: AppDatabase? = null
//
//        fun getAppDataBase(context: Context): AppDatabase? {
//            if (INSTANCE == null){
//                synchronized(AppDatabase::class){
//                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyDataBase(){
//            INSTANCE = null
//        }
//    }
//}


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
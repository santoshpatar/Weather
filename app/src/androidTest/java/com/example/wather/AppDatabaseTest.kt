package com.example.wather

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.wather.room.AppDatabase
import com.example.wather.room.dao.CityDao
import com.example.wather.room.entity.CityTable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private var cityDao: CityDao? = null
    private var db: AppDatabase? = null

    @Before
    fun onCreateDB() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        cityDao = db!!.cityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

 

    @Test
    fun CityDaoIsNOtNull() {
        Assert.assertNotNull(cityDao)
    }


}
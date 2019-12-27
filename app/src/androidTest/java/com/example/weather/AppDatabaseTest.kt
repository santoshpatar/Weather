package com.example.weather

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.weather.room.AppDatabase
import com.example.weather.room.dao.CityDao
import com.example.weather.room.entity.CityTable
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
    @Throws(Exception::class)
    fun insertCityTest(){
        var city = CityTable()
        city.areaName  = "TestArea";
        city.region = "TestRegion"
        city.country = "TestCountry"
        city.latitude = "122"
        city.longitude = "333"
        city.weatherUrl = ""
        city.timeStamp = 111111;
        cityDao?.insert(city)
        Assert.assertEquals(cityDao?.loadAllCity()?.size, 1)
        Assert.assertNotEquals(cityDao?.loadAllCity()?.size, 0)

    }

    @Test
    fun checkEmptyDB() {
        val cityList = cityDao?.loadAllCity()
        Assert.assertEquals(cityList?.size, 0)
        Assert.assertNotEquals(cityList?.size, 1)
    }

    


}
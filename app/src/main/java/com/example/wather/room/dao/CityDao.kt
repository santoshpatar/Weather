package com.example.wather.room.dao

import androidx.room.*
import com.example.wather.room.entity.CityTable

@Dao
interface  CityDao{

    @Query("SELECT * FROM city_item WHERE latitude = :lat AND longitude = :longi")
    fun findCityByLatitude(lat: String,longi:String): CityTable

    @Insert
    fun insert(vararg cityitem: CityTable)

    @Delete
    fun delete(ciyitem: CityTable)

    @Query("UPDATE city_item SET timeStamp= :time WHERE  latitude = :lat AND longitude = :longi")
    fun updateCitybyLatLong(lat: String,longi:String,time:Long)

    @Update
    fun updateCity(vararg cityitem: CityTable)


    @Query("SELECT * FROM city_item ORDER BY timeStamp DESC limit 10")
    fun loadAllCity(): Array<CityTable>
    
}
package com.example.wather.room

import androidx.room.*

@Dao
interface  CityDao{

    @Query("SELECT * FROM city_item")
    fun getAll(): List<CityTable>

    @Query("SELECT * FROM city_item WHERE latitude = :lat AND longitude = :longi")
    fun findCityByLatitude(lat: String,longi:String): CityTable

    @Insert
    fun insert(vararg cityitem: CityTable)

    @Delete
    fun delete(ciyitem: CityTable)

    @Update
    fun updateCity(vararg cityitem: CityTable)
}
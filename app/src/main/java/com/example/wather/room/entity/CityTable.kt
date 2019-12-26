package com.example.wather.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_item")
data class CityTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "areaName")
    var areaName: String = "NA",

    @ColumnInfo(name = "region")
    var region: String = "NA",

    @ColumnInfo(name = "country")
    var country: String = "NA",

    @ColumnInfo(name = "latitude")
    var latitude: String = "NA",

    @ColumnInfo(name = "longitude")
    var longitude: String = "NA",

    @ColumnInfo(name = "weatherUrl")
    var weatherUrl: String = "NA",

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long = 0

)
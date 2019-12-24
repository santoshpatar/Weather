package com.example.wather.data.source.remote.model

import android.os.Parcel
import android.os.Parcelable

class City() :Parcelable{

    var areaName: String = "NA"
    var country: String = "NA"

    var latitude: String = "0"
    var longitude: String = "0"

    var population: String = "0"
    var region: String = "NA"

    var weatherUrl: String = "NA"

    constructor(parcel: Parcel) : this() {
        areaName = parcel.readString()
        country = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
        population = parcel.readString()
        region = parcel.readString()
        weatherUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(areaName)
        parcel.writeString(country)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(population)
        parcel.writeString(region)
        parcel.writeString(weatherUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }


}
package com.hend.vehiclesmanager.utils

import android.arch.persistence.room.TypeConverter
import com.hend.vehiclesmanager.model.Coordinate
import com.google.gson.Gson

/**
 * Class which contains Type Converters to convert Coordinate model to an understandable object for Room
 */
class DataConverter {

    @TypeConverter
    fun fromCoordinates(coordinate: Coordinate?): String? {
        return Gson().toJson(coordinate)
    }

    @TypeConverter
    fun toCoordinates(coordinateString: String?): Coordinate? {
        return Gson().fromJson(coordinateString, Coordinate::class.java)
    }

}
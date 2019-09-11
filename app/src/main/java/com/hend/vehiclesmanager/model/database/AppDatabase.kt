package com.hend.vehiclesmanager.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.hend.vehiclesmanager.model.Vehicle
import com.hend.vehiclesmanager.model.VehicleDao
import com.hend.vehiclesmanager.utils.DataConverter

@Database(entities = [Vehicle::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}
package com.hend.vehiclesmanager.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface VehicleDao {
    @get:Query("SELECT * FROM vehicle")
    val all: List<Vehicle>

    @Insert
    fun insertAll(vararg users: Vehicle)
}
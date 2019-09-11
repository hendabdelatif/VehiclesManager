package com.hend.vehiclesmanager.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.hend.vehiclesmanager.model.database.AppDatabase
import com.hend.vehiclesmanager.ui.VehicleListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "vehicles").build()
            @Suppress("UNCHECKED_CAST")
            return VehicleListViewModel(db.vehicleDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
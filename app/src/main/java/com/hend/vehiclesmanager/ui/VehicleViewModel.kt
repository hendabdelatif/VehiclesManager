package com.hend.vehiclesmanager.ui

import android.arch.lifecycle.MutableLiveData
import com.hend.vehiclesmanager.base.BaseViewModel
import com.hend.vehiclesmanager.model.Vehicle

class VehicleViewModel: com.hend.vehiclesmanager.base.BaseViewModel() {
    private val vehicleId = MutableLiveData<String>()
    private val vehicleFleetType = MutableLiveData<String>()

    fun bind(vehicle: Vehicle){
        vehicleId.value = vehicle.id.toString()
        vehicleFleetType.value = vehicle.fleetType
    }

    fun getVehicleId():MutableLiveData<String>{
        return vehicleId
    }

    fun getVehicleFleetType():MutableLiveData<String>{
        return vehicleFleetType
    }

}
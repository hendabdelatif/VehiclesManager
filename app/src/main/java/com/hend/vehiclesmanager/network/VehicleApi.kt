package com.hend.vehiclesmanager.network

import android.database.Observable
import com.hend.vehiclesmanager.model.PoiList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface VehicleApi {

    /**
     * Get the list of the vehicles from the API
     */
    @GET("/")
    fun getVehicles(@Query("p1Lat") Latitude1: Double,
                    @Query("p1Lon") Longitude1: Double,
                    @Query("p2Lat") Latitude2: Double,
                    @Query("p2Lon") Longitude2: Double): Observable<PoiList>
}
package com.hend.vehiclesmanager.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for vehicle
 */
@Entity
@Parcelize
class Vehicle(@field:PrimaryKey val id: Int, val coordinate: Coordinate, val fleetType: String, val heading: Double) : Parcelable





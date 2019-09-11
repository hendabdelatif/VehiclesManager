package com.hend.vehiclesmanager.model

import android.arch.persistence.room.Entity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Class which provides a model for PoiList which contains list of vehicles
 */
@Entity
@Parcelize
class PoiList(val poiList : List<Vehicle>) : Parcelable
package com.hend.vehiclesmanager.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.hend.vehiclesmanager.R
import com.hend.vehiclesmanager.base.BaseViewModel
import com.hend.vehiclesmanager.model.Vehicle
import com.hend.vehiclesmanager.model.VehicleDao
import com.hend.vehiclesmanager.network.VehicleApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VehicleListViewModel(private val vehicleDao: VehicleDao) : BaseViewModel() {

    @Inject
    lateinit var vehicleApi: VehicleApi

    private lateinit var subscription: Disposable

    // Bounds of Hamburg
    var LATITUDE1: Double = 53.69486
    var LONGITUDE1: Double = 9.757589
    var LATITUDE2: Double = 53.394655
    var LONGITUDE2: Double = 10.099891

    val vehicleListAdapter: VehicleListAdapter = VehicleListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    val errorClickListener = View.OnClickListener { loadVehicles(LATITUDE1, LONGITUDE1, LATITUDE2, LONGITUDE2) }

    init {

        loadVehicles(LATITUDE1, LONGITUDE1, LATITUDE2, LONGITUDE2)
    }

    private fun loadVehicles(latitude1: Double, longitude1: Double, latitude2: Double, longitude2: Double) {

        subscription = Observable.fromCallable { vehicleDao.all }
            .concatMap { dbVehicleList ->
                if (dbVehicleList.isEmpty())
                    vehicleApi.getVehicles(latitude1, longitude1, latitude2, longitude2).concatMap { apiPoiList ->
                        vehicleDao.insertAll(*apiPoiList.poiList.toTypedArray())
                        Observable.just(apiPoiList.poiList)
                    }
                else
                    Observable.just(dbVehicleList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveVehicleListStart() }
            .doOnTerminate { onRetrieveVehicleListFinish() }
            .subscribe(
                { result -> onRetrieveVehicleListSuccess(result) },
                { onRetrieveVehicleListError() }
            )
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveVehicleListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveVehicleListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveVehicleListSuccess(vehicleList: List<Vehicle>) {
        vehicleListAdapter.updateVehicleList(vehicleList)
    }

    private fun onRetrieveVehicleListError() {
        errorMessage.value = R.string.vehicle_error
    }

}
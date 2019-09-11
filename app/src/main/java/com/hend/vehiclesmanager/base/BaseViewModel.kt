package com.hend.vehiclesmanager.base

import android.arch.lifecycle.ViewModel
import com.hend.vehiclesmanager.injection.NetworkModule
import com.hend.vehiclesmanager.injection.component.DaggerViewModelInjector
import com.hend.vehiclesmanager.injection.component.ViewModelInjector
import com.hend.vehiclesmanager.ui.VehicleListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is VehicleListViewModel -> injector.inject(this)
        }
    }
}
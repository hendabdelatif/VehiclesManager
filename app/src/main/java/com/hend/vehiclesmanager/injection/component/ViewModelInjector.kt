package com.hend.vehiclesmanager.injection.component

import com.hend.vehiclesmanager.injection.NetworkModule
import com.hend.vehiclesmanager.ui.VehicleListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified VehicleListViewModel.
     * @param vehicleListViewModel VehicleListViewModel in which to inject the dependencies
     */
    fun inject(vehicleListViewModel: VehicleListViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
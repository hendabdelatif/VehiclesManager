package com.hend.vehiclesmanager.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.hend.vehiclesmanager.R
import com.hend.vehiclesmanager.databinding.ActivityVehicleListBinding
import com.hend.vehiclesmanager.injection.ViewModelFactory

class VehicleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVehicleListBinding
    private lateinit var viewModel: VehicleListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_list)
        binding.vehicleList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.vehicleList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

//        viewModel = ViewModelProviders.of(this).get(VehicleListViewModel::class.java)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(VehicleListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}
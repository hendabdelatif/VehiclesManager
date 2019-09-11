package com.hend.vehiclesmanager.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hend.vehiclesmanager.R
import com.hend.vehiclesmanager.databinding.ItemVehicleBinding
import com.hend.vehiclesmanager.model.Vehicle
import com.hend.vehiclesmanager.ui.maps.MapsActivity
import com.hend.vehiclesmanager.utils.extension.listen

class VehicleListAdapter : RecyclerView.Adapter<VehicleListAdapter.ViewHolder>() {
    private lateinit var vehicleList: List<Vehicle>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListAdapter.ViewHolder {
        val binding: ItemVehicleBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_vehicle, parent, false)
        return ViewHolder(binding).listen { position, type ->
            val vehicleItem = vehicleList.get(position)

            val intent = Intent(parent.context, MapsActivity::class.java)
            intent.putExtra("coordinate", vehicleItem.coordinate)
            intent.putExtra("fleetType", vehicleItem.fleetType)
            startActivity(parent.context, intent, null)
        }

    }

    override fun onBindViewHolder(holder: VehicleListAdapter.ViewHolder, position: Int) {
        holder.bind(vehicleList[position])
    }

    override fun getItemCount(): Int {
        return if (::vehicleList.isInitialized) vehicleList.size else 0
    }

    fun updateVehicleList(vehicleList: List<Vehicle>) {
        this.vehicleList = vehicleList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemVehicleBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = VehicleViewModel()

        fun bind(vehicle: Vehicle) {
            viewModel.bind(vehicle)
            binding.viewModel = viewModel
        }
    }
}
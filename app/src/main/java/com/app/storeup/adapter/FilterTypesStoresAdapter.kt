package com.app.storeup.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.storeup.databinding.ItemStoresFiltersBinding
import com.app.storeup.model.entitys.DatesCategoriasModel

class FilterTypesStoresAdapter(private val storesList:List<DatesCategoriasModel>)
    :RecyclerView.Adapter<FilterTypesStoresAdapter.ViewHolder>() {

    class ViewHolder(private val binnding: ItemStoresFiltersBinding): RecyclerView.ViewHolder(binnding.root) {
        @SuppressLint("SetTextI18n")
        fun storesTypes(item: DatesCategoriasModel){
            binnding.tvName.text = "Name: ${item.nombre}"
            binnding.tvHorario.text = item.horario
            binnding.tvLocation.text = "Location: ${item.ubicacion}"
            binnding.tvPromotion.text = "Promotions: ${item.promocion}"
            binnding.tvProducts.text = "Products: ${item.productos}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterTypesStoresAdapter.ViewHolder {
        val view = ItemStoresFiltersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterTypesStoresAdapter.ViewHolder, position: Int) {
        val stores = storesList[position]
        holder.storesTypes(stores)
    }

    override fun getItemCount(): Int {
        return storesList.size
    }
}
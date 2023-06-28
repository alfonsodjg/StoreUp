package com.app.storeup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.app.storeup.R
import com.app.storeup.databinding.ItemTypeStoreBinding
import com.app.storeup.model.entitys.CategoriasModel

class TypeStoreAdapter(private val c: Context) : RecyclerView.Adapter<TypeStoreAdapter.ViewHolder>() {
    private var categorias: List<CategoriasModel> = emptyList()

    inner class ViewHolder(private val binding: ItemTypeStoreBinding) : RecyclerView.ViewHolder(binding.root) {
        val iv: ImageView =binding.ivImageStore
        fun typeCategorias(categorias: CategoriasModel) {
            binding.tvTypeStore.text = categorias.tipo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTypeStoreBinding.inflate(LayoutInflater.from(c), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val typeCategoria = categorias[position]
        holder.typeCategorias(typeCategoria)
        when (position) {
            0 -> {
                holder.iv.setImageResource(R.drawable.stores)
            }
            1 -> {
                holder.iv.setImageResource(R.drawable.abarrotes)
            }
            2 -> {
                holder.iv.setImageResource(R.drawable.refaccionarias)
            }
            3->{
                holder.iv.setImageResource(R.drawable.turismo)
            }
            4->{
                holder.iv.setImageResource(R.drawable.tecnologia)
            }
            5->{
                holder.iv.setImageResource(R.drawable.food)
            }
            6->{
                holder.iv.setImageResource(R.drawable.sports)
            }
            7->{
                holder.iv.setImageResource(R.drawable.beauty)
            }
            8->{
                holder.iv.setImageResource(R.drawable.others)
            }
        }
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    fun setData(categorias: List<CategoriasModel>) {
        this.categorias = categorias
        notifyDataSetChanged()
    }
}
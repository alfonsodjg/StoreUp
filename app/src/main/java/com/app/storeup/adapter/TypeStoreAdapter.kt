package com.app.storeup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.storeup.databinding.ItemTypeStoreBinding
import com.app.storeup.model.entitys.CategoriasModel

class TypeStoreAdapter(private val c: Context) : RecyclerView.Adapter<TypeStoreAdapter.ViewHolder>() {
    private var categorias: List<CategoriasModel> = emptyList()

    inner class ViewHolder(private val binding: ItemTypeStoreBinding) : RecyclerView.ViewHolder(binding.root) {
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
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    fun setData(categorias: List<CategoriasModel>) {
        this.categorias = categorias
        notifyDataSetChanged()
    }
}
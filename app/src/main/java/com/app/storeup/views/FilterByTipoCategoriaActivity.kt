package com.app.storeup.views

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.storeup.R
import com.app.storeup.adapter.FilterTypesStoresAdapter
import com.app.storeup.databinding.ActivityFilterByTipoCategoriaBinding
import com.app.storeup.viewmodels.FilterByTipoCategoriaviewModel


class FilterByTipoCategoriaActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFilterByTipoCategoriaBinding
    private val viewModel: FilterByTipoCategoriaviewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterByTipoCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tipoCategoria = intent.getStringExtra("tipoCategoria").toString()
        println("tipo de tienda: $tipoCategoria")

        viewModel.isLoading.observe(this){
            binding.pbLoading.isVisible = it

            if (it){
                binding.rvStoresTypes.visibility = View.GONE
            }else{
                binding.rvStoresTypes.visibility = View.VISIBLE
            }
        }
        viewModel.storesList.observe(this){
            println("stores en activity: ${viewModel.storesList.value}")
            val adapter = FilterTypesStoresAdapter(it)
            binding.rvStoresTypes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvStoresTypes.adapter = adapter
        }
        viewModel.alert.observe(this){
            if (it){
                val alert =  AlertDialog.Builder(this)
                    .setMessage("No hay tiendas disponibles")
                    .setPositiveButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }
                val dialog = alert.create()
                dialog.setTitle("Alert")
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }
        }
        viewModel.onSetStores(tipoCategoria)
    }
}
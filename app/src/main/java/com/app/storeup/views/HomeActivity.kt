package com.app.storeup.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.app.storeup.R
import com.app.storeup.adapter.TypeStoreAdapter
import com.app.storeup.databinding.ActivityHomeBinding
import com.app.storeup.viewmodels.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var adapter: TypeStoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        val animSearch = AnimationUtils.loadAnimation(this, R.anim.anim_rigth)
        binding.svHomeActivity.startAnimation(animSearch)

        adapter = TypeStoreAdapter(this)
        binding.rvTypeStores.layoutManager = GridLayoutManager(this, 2)
        binding.rvTypeStores.setHasFixedSize(true)
        binding.rvTypeStores.adapter = adapter

        // Observa los cambios en la lista de categorías
        viewModel.categorias.observe(this) { categorias ->
            adapter.setData(categorias)
        }
        viewModel.showStores()
    }
}






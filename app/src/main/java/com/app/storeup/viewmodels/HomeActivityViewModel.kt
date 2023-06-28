package com.app.storeup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.model.entitys.CategoriasModel
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivityViewModel : ViewModel() {
    private val _operation = MutableLiveData<Boolean>()
    val operation: LiveData<Boolean> get() = _operation

    private val _categorias = MutableLiveData<List<CategoriasModel>>()
    val categorias: LiveData<List<CategoriasModel>> get() = _categorias

    fun showStores() {
        viewModelScope.launch {
            try {
                val call = ApiClient.getInstance().create(IRetrofit::class.java)
                val response = withContext(Dispatchers.IO) {
                    call.getCategorias()
                }
                println("Code type categorias")
                if (response.isSuccessful) {
                    _operation.value = true
                    val categoriasList = response.body()
                    _categorias.value = categoriasList!!
                    println("Cantidad de categorías: ${categoriasList.size}")
                }
            } catch (e: Exception) {
                println("Exception al recuperar stores: $e")
                _operation.value = false
            }
        }
    }
}
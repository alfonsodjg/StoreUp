package com.app.storeup.views.ui.home

import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.R
import com.app.storeup.model.entitys.CategoriasModel
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    var operation=MutableLiveData<Boolean>()
    var text=MutableLiveData<String>()
    var categoriasList = MutableLiveData<List<CategoriasModel>>()

    //Funcion que recupera la lista de categorias y los muestra en un spinner
    fun getCategorias(){
        viewModelScope.launch {
            try {
                val call=ApiClient.getInstance().create(IRetrofit::class.java)
                val response= withContext(Dispatchers.IO){
                    call.getCategorias()
                }
                println("Code spiner categorias: ${response.code()}")
                if (response.isSuccessful){
                    val tiposCategoria = response.body()
                    val categorias = tiposCategoria ?: emptyList()
                    categoriasList.value = listOf(CategoriasModel(0, "Selecciona una opción")) + categorias
                }
            }catch (e:Exception){
                println("Error exception getCategorias: $e")
            }
        }
    }
}
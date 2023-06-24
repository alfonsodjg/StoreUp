package com.app.storeup.views.ui.home

import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.R
import com.app.storeup.model.entitys.CategoriasModel
import com.app.storeup.model.entitys.DatesCategoriasModel
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    var operation = MutableLiveData<Boolean>()
    var text = MutableLiveData<String>()
    var categoriasList = MutableLiveData<List<CategoriasModel>>()
    var edtName = MutableLiveData<String>()
    var edtTime = MutableLiveData<String>()
    var edtPromotion = MutableLiveData<String>()
    var spTypeStore = MutableLiveData<String>()
    var selectedCategoryId = MutableLiveData<Int>()
    var edtLocation = MutableLiveData<String>()
    var edtProducts = MutableLiveData<String>()

    // Funcion que recupera la lista de categorias y los muestra en un spinner
    fun getCategorias() {
        viewModelScope.launch {
            try {
                val call = ApiClient.getInstance().create(IRetrofit::class.java)
                val response = withContext(Dispatchers.IO) {
                    call.getCategorias()
                }
                println("Code spiner categorias: ${response.code()}")
                if (response.isSuccessful) {
                    val tiposCategoria = response.body()
                    val categorias = tiposCategoria ?: emptyList()
                    categoriasList.value = listOf(CategoriasModel(0, "Selecciona una opción")) + categorias

                    // Asignar el ID de la opción seleccionada
                    selectedCategoryId.value = categorias[0].id_categoria
                }
            } catch (e: Exception) {
                println("Error exception getCategorias: $e")
            }
        }
    }

    fun registerDatas() {
        if (validar()) {
            val datas = DatesCategoriasModel(
                edtName.value!!,
                edtTime.value!!,
                edtPromotion.value!!,
                edtLocation.value!!,
                edtProducts.value!!,
                CategoriasModel(selectedCategoryId.value!!, "") // Crear un objeto CategoriasModel con el id seleccionado
            )
            viewModelScope.launch {
                try {
                    val call = ApiClient.getInstance().create(IRetrofit::class.java)
                    val response = withContext(Dispatchers.IO) {
                        call.insertDatesCategoria(datas)
                    }
                    println("Code register datas: ${response.code()}")
                    if (response.isSuccessful) {
                        operation.value = true
                    }
                } catch (e: Exception) {
                    println("Error register datas $e")
                    operation.value = false
                }
            }
        } else {
            operation.value = false
        }
    }

    private fun validar(): Boolean {
        return !(edtName.value.isNullOrEmpty()
                || edtTime.value.isNullOrEmpty()
                || edtPromotion.value.isNullOrEmpty()
                || edtLocation.value.isNullOrEmpty()
                || selectedCategoryId.value == 0
                )
    }
}
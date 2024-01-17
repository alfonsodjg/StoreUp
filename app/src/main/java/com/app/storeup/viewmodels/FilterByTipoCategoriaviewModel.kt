package com.app.storeup.viewmodels

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.storeup.model.entitys.DatesCategoriasModel
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class FilterByTipoCategoriaviewModel(): ViewModel() {
    var isLoading = MutableLiveData<Boolean>(false)
    var storesList = MutableLiveData<List<DatesCategoriasModel>>(emptyList())
    var alert = MutableLiveData<Boolean>(false)

    fun onSetStores(tipoCategoria:String){
        viewModelScope.launch {
            try {
                isLoading.value = true
                val call = ApiClient.getInstance().create(IRetrofit::class.java)
                withContext(Dispatchers.IO){
                    val response = call.getFilterStoresByTipo(tipoCategoria)

                    if (response.isSuccessful){
                        val body = response.body()
                        withContext(Dispatchers.Main){
                            if (body!!.isNotEmpty()){
                                isLoading.value = false
                                storesList.value= response.body()
                            }else{
                                isLoading.value = true

                                alert.value = true
                            }
                        }
                    }
                }
            }catch (e:IOException){
                println("Error: $e")
            }
        }
    }
}


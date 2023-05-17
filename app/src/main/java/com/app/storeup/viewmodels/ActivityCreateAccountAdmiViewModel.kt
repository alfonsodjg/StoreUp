package com.app.storeup.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.model.entitys.RegisterUserAdmi
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityCreateAccountAdmiViewModel:ViewModel() {
    var edtName=MutableLiveData<String>()
    var edtMail=MutableLiveData<String>()
    var edtPass=MutableLiveData<String>()
    var operationSuccessful=MutableLiveData<Boolean>()

    fun registerAdmi() {
        if (validation()) {
            val registerAdmi = RegisterUserAdmi(0, edtName.value!!, edtMail.value!!, edtPass.value!!)
            viewModelScope.launch {
                try {
                    val retrofit = ApiClient.getInstance()
                    val service = retrofit.create(IRetrofit::class.java)
                    val response = withContext(Dispatchers.IO) { service.insertAdmi(registerAdmi) }
                    println("Code: ${response.code()}")
                    if(response.isSuccessful) {
                        operationSuccessful.value = true
                    }
                } catch (e: Exception) {
                    operationSuccessful.value = false
                    println(e.printStackTrace())
                }
            }
        } else {
            operationSuccessful.value = false
        }
    }
    fun validation():Boolean{
        return !(
                edtName.value.isNullOrEmpty()
                        || edtMail.value.isNullOrEmpty()
                        || edtPass.value.isNullOrEmpty()
                )
    }
}
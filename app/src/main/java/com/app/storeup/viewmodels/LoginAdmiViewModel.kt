package com.app.storeup.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.model.entitys.LoginAuthAdmi
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginAdmiViewModel:ViewModel() {
    var email=MutableLiveData<String>()
    var password=MutableLiveData<String>()
    var operation=MutableLiveData<Boolean>()
    var errorCredentials= MutableLiveData<String>()

    fun singIng(){
        if(validar()){
            val user=LoginAuthAdmi(email.value!!,password.value!!)
            viewModelScope.launch {
                try {
                    val call=ApiClient.getInstance().create(IRetrofit::class.java)
                    val response= withContext(Dispatchers.IO){
                        call.loginAdmi(user)
                    }
                    println("Code login admi: ${response.code()}")
                    if(response.isSuccessful){
                        operation.value=true
                    }else if(response.code()==401){
                        errorCredentials.postValue("Error user or password")
                        operation.value=false
                    }else if(response.code()==404){
                        errorCredentials.postValue("User not found")
                        operation.value=false
                    }
                }catch (e:Exception){
                    println("Error login admi: $e")
                }
            }
        }else{
            operation.value=false
        }
    }
    private fun validar():Boolean{
        return !(
                       email.value.isNullOrEmpty()
                        ||
                        password.value.isNullOrEmpty()
                )
    }
}
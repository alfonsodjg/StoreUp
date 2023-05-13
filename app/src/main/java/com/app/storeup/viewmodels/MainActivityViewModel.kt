package com.app.storeup.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.storeup.model.entitys.LoginAuth
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel:ViewModel() {
    var passSingIn=MutableLiveData<String>()
    var mailSingIn=MutableLiveData<String>()
    var operationSuccesful=MutableLiveData<Boolean>()

    fun singIn(){
        if(validation()){
            val loginDatas=LoginAuth(mailSingIn.value!!,passSingIn.value!!)
            val call=ApiClient
                .getInstance()
                .create(IRetrofit::class.java)
                .loginUser(loginDatas)

            call.enqueue(object :Callback<LoginAuth>{
                override fun onResponse(call: Call<LoginAuth>, response: Response<LoginAuth>) {
                    println("Code: ${response.code()}")
                    if(response.isSuccessful){
                        operationSuccesful.value=true
                    }
                }

                override fun onFailure(call: Call<LoginAuth>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            })
        }else{
            operationSuccesful.value=false
        }
    }
    private fun validation(): Boolean {
        return !(
                        passSingIn.value.isNullOrEmpty() ||
                        mailSingIn.value.isNullOrEmpty()
                )
    }
}
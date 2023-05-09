package com.app.storeup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.storeup.model.RegisterUser
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentCreateAccountViewModel:ViewModel() {
    private val _removeFragmentEvent = MutableLiveData<Unit>()
    val removeFragmentEvent: LiveData<Unit> get() = _removeFragmentEvent
    var id=MutableLiveData<Int>()
    var edtMail=MutableLiveData<String>()
    var edtPass=MutableLiveData<String>()
    var operationSuccesful=MutableLiveData<Boolean>()

    fun registerUser(){
        if(validation()){
            val user=RegisterUser(0,edtMail.value!!,edtPass.value!!)

            val call= ApiClient
                .getInstance()
                .create(IRetrofit::class.java)
                .registerUser(user)

            call.enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    println("Code: ${response.code()}")
                   if(response.isSuccessful){
                       operationSuccesful.value=true
                   }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("Error: + ${t.message}")
                }
            })
        }else{
            operationSuccesful.value=false
        }
    }
    fun onBackButtonClick() {
        _removeFragmentEvent.value = Unit
    }
    fun validation(): Boolean {
        return !(
                        edtMail.value.isNullOrEmpty() ||
                        edtPass.value.isNullOrEmpty()
                )
    }

}
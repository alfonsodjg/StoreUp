package com.app.storeup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storeup.model.RegisterUser
import com.app.storeup.model.remote.ApiClient
import com.app.storeup.model.remote.IRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
            try {
                viewModelScope.launch {
                    val retrofit=ApiClient.getInstance()
                    val service=retrofit.create(IRetrofit::class.java)
                    val response= withContext(Dispatchers.IO){
                        service.registerUser(user)
                    }
                    println("Code: ${response.code()}")
                    if(response.isSuccessful){
                        operationSuccesful.value=true


                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
                operationSuccesful.value=false
            }
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
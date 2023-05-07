package com.app.storeup.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    var passSingIn=MutableLiveData<String>()
    var mailSingIn=MutableLiveData<String>()
    var operationSuccesful=MutableLiveData<Boolean>()

    fun singIn(){
        if(validation()){

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
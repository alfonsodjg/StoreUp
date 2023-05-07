package com.app.storeup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentCreateAccountViewModel:ViewModel() {
    private val _removeFragmentEvent = MutableLiveData<Unit>()
    val removeFragmentEvent: LiveData<Unit> get() = _removeFragmentEvent
    var id=MutableLiveData<Int>()
    var edtName=MutableLiveData<String>()
    var edtMail=MutableLiveData<String>()
    var operationSuccesful=MutableLiveData<Boolean>()

    fun registerUser(){
        if(validation()){

        }else{
            operationSuccesful.value=false
        }
    }
    fun onBackButtonClick() {
        _removeFragmentEvent.value = Unit
    }
    fun validation(): Boolean {
        return !(
                        edtName.value.isNullOrEmpty() ||
                        edtMail.value.isNullOrEmpty()
                )
    }

}
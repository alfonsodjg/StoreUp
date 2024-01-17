package com.app.storeup.views.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Elizabeth Ferrer Paez\nInstituto Tecnologico Superior de Zongolica"
    }
    val text: LiveData<String> = _text
}
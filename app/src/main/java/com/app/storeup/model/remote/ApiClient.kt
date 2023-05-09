package com.app.storeup.model.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): Retrofit {
        val retrofit= Retrofit.Builder()
            .baseUrl("http://192.168.1.73:8095/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}
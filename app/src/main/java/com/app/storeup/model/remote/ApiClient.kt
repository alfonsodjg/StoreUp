package com.app.storeup.model.remote


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.73:8095/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}
package com.app.storeup.model.remote

import com.app.storeup.model.RegisterUser
import com.app.storeup.model.entitys.LoginAuth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IRetrofit {
    //Registrar usuario
    @POST("api/StoreUp/registerUser")
    fun registerUser(@Body user:RegisterUser):Call<Void>

    //Inicio de sesion
    @POST("api/login")
    fun loginUser(@Body loginAuth: LoginAuth):Call<LoginAuth>

}
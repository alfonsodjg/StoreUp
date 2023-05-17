package com.app.storeup.model.remote

import com.app.storeup.model.RegisterUser
import com.app.storeup.model.entitys.LoginAuth
import com.app.storeup.model.entitys.RegisterUserAdmi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IRetrofit {
    //Registrar usuario
    @POST("api/StoreUp/registerUser")
    suspend fun registerUser(@Body user:RegisterUser):Response<Void>

    //Inicio de sesion
    @POST("api/login")
    fun loginUser(@Body loginAuth: LoginAuth):Call<LoginAuth>

    //Registrar administrador
    @POST("api/inserUser/admi")
    suspend fun insertAdmi(@Body admi:RegisterUserAdmi):Response<Void>
}
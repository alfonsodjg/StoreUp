package com.app.storeup.model.remote

import com.app.storeup.model.entitys.RegisterUser
import com.app.storeup.model.entitys.CategoriasModel
import com.app.storeup.model.entitys.DatesCategoriasModel
import com.app.storeup.model.entitys.LoginAuth
import com.app.storeup.model.entitys.LoginAuthAdmi
import com.app.storeup.model.entitys.RegisterUserAdmi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IRetrofit {
    //Registrar usuario
    @POST("api/StoreUp/registerUser")
    suspend fun registerUser(@Body user: RegisterUser):Response<Void>

    //Inicio de sesion
    @POST("api/login")
    fun loginUser(@Body loginAuth: LoginAuth):Call<LoginAuth>

    //Registrar administrador
    @POST("api/inserUser/admi")
    suspend fun insertAdmi(@Body admi:RegisterUserAdmi):Response<Void>

    //-----------------Metodod administrador------------
    //Login Administrador
    @POST("api/loginAdmi")
    suspend fun loginAdmi(@Body credentials:LoginAuthAdmi):Response<LoginAuthAdmi>

    //Lista de tipos de categoria
    @GET("api/getAllTipoCategorias")
    suspend fun getCategorias():Response<List<CategoriasModel>>

    //Metodo para registrar datos de categoria
    @POST("api/insert/dates/categoria")
    suspend fun insertDatesCategoria(@Body datos:DatesCategoriasModel):Response<Void>

    //Metodo que filtra las tiendas por tipo de tienda ---------------------USERS-------------
    @GET("api/getListasByTipo")
    suspend fun getFilterStoresByTipo(@Query("tipoCategoria") tipoCategoria:String): Response<List<DatesCategoriasModel>>
}
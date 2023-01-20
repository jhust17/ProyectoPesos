package com.proy.kmanager1

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
    @GET("users")
fun getUsers(): Call<List<ListElement>>

    @POST("users")
    fun postUsers(@Body lista: List<ListElement>): Call<List<ListElement>>

}
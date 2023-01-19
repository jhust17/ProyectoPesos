package com.proy.kmanager1.io

import com.google.gson.JsonObject
import retrofit2.Call
import com.proy.kmanager1.io.response.LoginResponse
import com.proy.kmanager1.io.response.LogoutResponse
import com.proy.kmanager1.model.Logg
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST( value = "login")
    fun postLogin(@Body logg: Logg):
            Call<LoginResponse>

    @POST(value = "logout")
    fun postLogout(@Header(value = "Authorization") authHeader: String):
            Call<Void>
    companion object Factory{
        private const val BASE_URL = "http://10.0.0.4:4000/api/auth/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
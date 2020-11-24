package com.project.threeschool.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var instance: Retrofit? = null
    private var API : Service? = null

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                    .baseUrl("http://172.30.1.13:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return instance!!
    }

    fun getService() : Service{
        if(API == null){
            API = instance?.create(Service::class.java)
        }
        return API!!
    }

}
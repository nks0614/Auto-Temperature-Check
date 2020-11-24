package com.project.threeschool.network

import com.project.threeschool.model.Members
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("list")
    fun getList() : Call<Members>

}
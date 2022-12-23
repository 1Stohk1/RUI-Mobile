package com.mario.rui.network

import com.mario.rui.model.Task
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @GET("/poi")
    fun getTasks(): Call<List<Task>?>

}
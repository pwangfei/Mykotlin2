package com.example.mykotlin

import retrofit2.http.GET


val Api: ApiInterface by lazy {
    retrofit.create(ApiInterface::class.java)
}


interface ApiInterface {
    @GET("/article/listproject/0/json")
    suspend fun getListProject(): BaseResponse<ListProjectBean?>
}
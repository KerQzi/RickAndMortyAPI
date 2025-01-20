package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data

import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>
}
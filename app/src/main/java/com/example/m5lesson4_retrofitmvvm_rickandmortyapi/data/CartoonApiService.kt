package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data

import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.BaseResponse
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

    @GET
    fun getEpisode(@Url url: String): Call<Episode>
}
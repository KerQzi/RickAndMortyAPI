package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)
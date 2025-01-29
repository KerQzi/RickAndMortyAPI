package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
): Serializable
package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val status: String,
    val species: String,
    val gender: String,
    val location: String,
    val origin: String,
    val imageBase64: String?,
    val firstEpisodeName: String?,
):Serializable
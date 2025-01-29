package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.interfaces

import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes.Episode
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterEntity

interface OnItemClick {
    fun onItemClick(character: Character){
        println() //добавил хоть что то, чтобы имплементация была необязательна
    }
    fun onItemClick(character: CharacterEntity){
        println()
    }
}
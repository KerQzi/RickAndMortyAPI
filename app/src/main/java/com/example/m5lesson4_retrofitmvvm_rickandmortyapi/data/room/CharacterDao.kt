package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM characters")
    fun getAllViewedCharacters(): LiveData<List<CharacterEntity>>
}
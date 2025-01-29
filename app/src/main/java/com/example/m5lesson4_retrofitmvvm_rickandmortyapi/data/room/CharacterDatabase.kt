    package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room

    import androidx.room.Database
    import androidx.room.RoomDatabase


    @Database(entities = [CharacterEntity::class], version = 4)
    abstract class CharacterDatabase : RoomDatabase() {

        abstract fun charactersDao(): CharacterDao
    }
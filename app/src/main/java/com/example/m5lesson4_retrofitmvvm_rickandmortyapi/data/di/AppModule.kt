package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.CartoonApiService
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterDao
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): CartoonApiService {
        return retrofit.create(CartoonApiService::class.java)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("onboarding_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "rick_and_morty_database"
        )
            .fallbackToDestructiveMigration()// Добавь миграции, если есть
            .build()
    }

    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.charactersDao()
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}
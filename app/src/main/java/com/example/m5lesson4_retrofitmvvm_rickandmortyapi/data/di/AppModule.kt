package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.CartoonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
}
package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.RetrofitInstance
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.BaseResponse
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import retrofit2.Call
import retrofit2.Response

class CharactersViewModel : ViewModel(){

    private val api = RetrofitInstance.api

    private val _charactersData = MutableLiveData<List<Character>>()
    val charactersData: LiveData<List<Character>> get() = _charactersData

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String> get() = _errorData

    fun getCharacters() {
        api.getCharacters().enqueue(object : retrofit2.Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        _charactersData.postValue(it.characters)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                _errorData.postValue(t.localizedMessage ?: "Unknown error")
            }

        })
    }
}
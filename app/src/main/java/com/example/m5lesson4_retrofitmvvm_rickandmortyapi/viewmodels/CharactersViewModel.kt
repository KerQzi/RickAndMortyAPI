package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.viewmodels

import ImageUtils
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.CartoonApiService
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.BaseResponse
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.Character
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes.Episode
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterDao
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val api: CartoonApiService,
    private val characterDao: CharacterDao
) : ViewModel() {

    private val _charactersData = MutableLiveData<List<Character>>()
    val charactersData: LiveData<List<Character>> get() = _charactersData

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String> get() = _errorData

    private val _episodeName = MutableLiveData<String>()
    val episodeName: LiveData<String> get() = _episodeName

    fun getCharacters() {
        api.getCharacters().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    response.body()?.characters?.let {
                        _charactersData.postValue(it)
                    } ?: run {
                        _errorData.postValue("No characters found")
                    }
                } else {
                    _errorData.postValue("Failed to fetch characters: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                _errorData.postValue(t.localizedMessage ?: "Unknown error")
            }
        })
    }

    fun getEpisodeNameForCharacter(episodeUrl: String, callback: (String) -> Unit) {
        api.getEpisodeName(episodeUrl).enqueue(object : Callback<Episode> {
            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                if (response.isSuccessful) {
                    val episodeName = response.body()?.name ?: "???"
                    callback(episodeName)
                } else {
                    callback("???")
                }
            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                callback("???")
            }
        })
    }


    fun saveViewedCharacter(character: Character) {
        character.episode?.firstOrNull()?.let { episodeUrl ->
            getEpisodeNameForCharacter(episodeUrl) { episodeName ->
                viewModelScope.launch(Dispatchers.IO) {
                    // Создаем объект CharacterEntity для сохранения в базу данных
                    val entity = CharacterEntity(
                        id = character.id,
                        name = character.name,
                        status = character.status ?: "Unknown",
                        species = character.species ?: "Unknown",
                        gender = character.gender ?: "Unknown",
                        location = character.location?.name ?: "Unknown",
                        firstEpisodeName = episodeName,
                        origin = character.origin?.name ?: "Unknown",
                        imageBase64 = character.image?.let { ImageUtils.urlToBase64(it) }
                    )

                    // Сохраняем в базу данных
                    characterDao.insertCharacter(entity)
                }
            }
        }
    }


    fun getViewedCharacters(): LiveData<List<CharacterEntity>> {
        return characterDao.getAllViewedCharacters()
    }


}
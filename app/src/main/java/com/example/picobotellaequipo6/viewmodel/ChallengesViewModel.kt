package com.example.picobotellaequipo6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.model.Pokemon
import com.example.picobotellaequipo6.repository.ChallengesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengesViewModel(application: Application):AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val challengesRepository = ChallengesRepository(context)

    private val _listInventory = MutableLiveData<MutableList<Challenges>>()
    val listInventory: LiveData<MutableList<Challenges>> get() = _listInventory

    private val _listPokemons = MutableLiveData<MutableList<Pokemon>>()
    val listPokemons: LiveData<MutableList<Pokemon>> = _listPokemons

    suspend fun saveInventory(challenges: Challenges) {
        withContext(Dispatchers.IO) {
            challengesRepository.saveInventory(challenges)
            // Fetch the updated list from the repository
            val updatedList = challengesRepository.getListInventory()
            // Update the LiveData
            _listInventory.postValue(updatedList)
        }
    }

    fun getListInvetory(){
        viewModelScope.launch {
            _listInventory.value = challengesRepository.getListInventory()

        }
    }

    fun getPokemons() {
        viewModelScope.launch {
            _listPokemons.value = challengesRepository.getPokemons()
        }
    }
}
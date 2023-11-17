package com.example.picobotellaequipo6.repository

import android.content.Context
import com.example.picobotellaequipo6.data.ChallengesDB
import com.example.picobotellaequipo6.data.ChallengesDao
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.model.Pokemon
import com.example.picobotellaequipo6.webservice.ApiService
import com.example.picobotellaequipo6.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengesRepository(val context:Context) {
    private var  challengesDao: ChallengesDao = ChallengesDB.getDatabase(context).challengesDao()
    private var apiService: ApiService = ApiUtils.getApiService()

    suspend fun saveInventory(challenges: Challenges){
        withContext(Dispatchers.IO){
            challengesDao.saveInventory(challenges)
        }
    }
    suspend fun getListInventory():MutableList<Challenges>{
        return withContext(Dispatchers.IO){
            challengesDao.getListInventory()
        }
    }

    suspend fun getPokemons(): MutableList<Pokemon> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPokemons()
                response.pokemon
            } catch (e: Exception) {
                // Manejar el error
                e.printStackTrace()
                mutableListOf()
            }
        }
    }

}
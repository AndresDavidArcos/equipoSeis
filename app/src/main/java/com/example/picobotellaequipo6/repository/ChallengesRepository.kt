package com.example.picobotellaequipo6.repository

import android.content.Context
import com.example.picobotellaequipo6.data.ChallengesDB
import com.example.picobotellaequipo6.data.ChallengesDao
import com.example.picobotellaequipo6.model.Challenges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengesRepository(val context:Context) {
    private var  challengesDao: ChallengesDao = ChallengesDB.getDatabase(context).challengesDao()

    suspend fun getListInventory():MutableList<Challenges>{
        return withContext(Dispatchers.IO){
            challengesDao.getListInventory()
        }
    }

}
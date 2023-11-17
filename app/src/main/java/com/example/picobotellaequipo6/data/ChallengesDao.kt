package com.example.picobotellaequipo6.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.picobotellaequipo6.model.Challenges
//2:23:00 ver base
@Dao
interface ChallengesDao {
    @Query("SELECT * FROM Challenges")
    suspend fun getListInventory(): MutableList<Challenges>
    @Delete
    suspend fun deleteChallenge(challenge: Challenges)
}
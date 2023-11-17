package com.example.picobotellaequipo6.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picobotellaequipo6.model.Challenges
//2:23:00 ver base
@Dao
interface ChallengesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInventory(challenges: Challenges)
    @Query("SELECT * FROM Challenges")
    suspend fun getListInventory(): MutableList<Challenges>
    @Update
    suspend fun updateChallenge(challenge: Challenges)
}
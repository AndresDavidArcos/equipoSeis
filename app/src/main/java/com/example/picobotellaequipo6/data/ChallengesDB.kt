package com.example.picobotellaequipo6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.utils.Constants.NAME_BD

@Database(entities = [Challenges::class], version = 1)
abstract class ChallengesDB : RoomDatabase() {

    abstract fun challengesDao(): ChallengesDao

    companion object{
        fun getDatabase(context: Context): ChallengesDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ChallengesDB::class.java,
                NAME_BD
            ).build()
        }
    }
}
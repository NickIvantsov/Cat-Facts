package com.example.android.catfacts.app.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1, exportSchema = false)
abstract class CatFactsDatabase : RoomDatabase(){
    abstract fun catFactsDao(): ICatDao
}
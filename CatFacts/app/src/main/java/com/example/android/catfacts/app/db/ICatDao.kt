package com.example.android.catfacts.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ICatDao {
    @Query("SELECT * FROM cat_facts_table")
    fun getCatFacts(): List<CatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catFacts: CatEntity)
}
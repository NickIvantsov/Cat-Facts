package com.example.android.catfacts.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ICatDao {
    @Query("SELECT * FROM cat_facts_table")
    fun getCatFacts(): Flow<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catFacts: CatEntity)

    @Query("DELETE FROM cat_facts_table")
    suspend fun deleteAll()
}
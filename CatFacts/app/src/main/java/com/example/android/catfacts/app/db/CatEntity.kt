package com.example.android.catfacts.app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_facts_table")
data class CatEntity(
    @PrimaryKey @ColumnInfo(name = "cat_facts")
    val catFacts: String
)
package com.example.android.catfacts.app.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_facts_table")
data class CatEntity(
    @PrimaryKey @ColumnInfo(name = "cat_facts")
    val catFacts: String,
    val photo: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatEntity

        if (catFacts != other.catFacts) return false
        if (!photo.contentEquals(other.photo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = catFacts.hashCode()
        result = 31 * result + photo.contentHashCode()
        return result
    }

}
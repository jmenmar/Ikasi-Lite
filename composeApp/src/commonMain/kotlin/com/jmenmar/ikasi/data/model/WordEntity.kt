package com.jmenmar.ikasi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmenmar.ikasi.domain.model.Word

@Entity(tableName = "word")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val meaning: String,
    val notes: String?,
) {
    companion object {
        fun WordEntity.toDomain() = Word(
            id = id,
            title = title,
            meaning = meaning,
            notes = notes,
        )
    }
}

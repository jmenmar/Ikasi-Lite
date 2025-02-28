package com.jmenmar.ikasi.domain.model

import com.jmenmar.ikasi.data.model.WordEntity

data class Word(
    val id: Int? = null,
    val title: String,
    val meaning: String,
    val notes: String?,
) {
    companion object {
        fun Word.toEntity() = WordEntity(
            title = title,
            meaning = meaning,
            notes = notes,
        )
    }
}

package com.jmenmar.ikasi.presentation.screens.vocabulary

import com.jmenmar.ikasi.domain.model.Word

data class VocabularyState(
    val isLoading: Boolean = false,
    val word: String = "",
    val meaning: String = "",
    val notes: String? = null,
    val allVocabulary: List<Word> = emptyList(),
    val filteredVocabulary: List<Word> = emptyList(),
)

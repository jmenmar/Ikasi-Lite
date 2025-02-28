package com.jmenmar.ikasi.presentation.screens.flashcards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlashcardsViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(FlashcardsState())
    val state: StateFlow<FlashcardsState> = _state

    init {
        getActivities()
    }

    private fun getActivities() {
        viewModelScope.launch {
            ikasiRepository.getAllWords().collect {
                _state.value = _state.value.copy(vocabulary = it.shuffled())
            }
        }
    }

    fun startFlashcards(){
        _state.value = _state.value.copy(isStarted = true)
    }

    fun onShowMeaning() {
        _state.value = _state.value.copy(isWordShown = true)
    }

    fun onNextWord(isCorrect: Boolean) {
        val newIndex = if (_state.value.index < _state.value.vocabulary.size - 1) {
            _state.value.index + 1
        } else {
            0
        }
        if (isCorrect) {
            _state.value = _state.value.copy(correctAnswers = _state.value.correctAnswers + 1)
        } else {
            _state.value = _state.value.copy(incorrectAnswers = _state.value.incorrectAnswers + 1)
        }

        val finished = newIndex == 0

        _state.value = _state.value.copy(
            isFinished = finished,
            index = newIndex,
            isWordShown = false,
            incorrectAnswers = _state.value.incorrectAnswers
        )
    }
}
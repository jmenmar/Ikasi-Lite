package com.jmenmar.ikasi.presentation.screens.vocabulary

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.components.BasicTextField
import com.jmenmar.ikasi.presentation.screens.vocabulary.components.AddVocabularyView
import com.jmenmar.ikasi.presentation.screens.vocabulary.components.VocabularyOverview
import com.jmenmar.ikasi.presentation.screens.vocabulary.components.WordCard
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.vocabulary_search_add
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun VocabularyScreen(
    innerPadding: PaddingValues,
    viewModel: VocabularyViewModel = koinViewModel<VocabularyViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    VocabularyView(
        innerPadding = innerPadding,
        vocabulary = state.filteredVocabulary,
        totalWords = state.allVocabulary.size,
        word = state.word,
        meaning = state.meaning,
        notes = state.notes,
        onWordChange = { viewModel.onNewWordTitleChange(it) },
        onMeaningChange = { viewModel.onNewWordMeaningChange(it) },
        onNotesChange = { viewModel.onNewWordNotesChange(it) },
        onAddNewWord = { viewModel.onAddNewWord() },
        onDeleteWord = { viewModel.onDeleteWord(it) },
        onClearWordField = { viewModel.clearForm() }
    )
}

@Composable
fun VocabularyView(
    innerPadding: PaddingValues,
    vocabulary: List<Word> = emptyList(),
    totalWords: Int = 0,
    word: String,
    meaning: String,
    notes: String?,
    onWordChange: (String) -> Unit = {},
    onMeaningChange: (String) -> Unit = {},
    onNotesChange: (String) -> Unit = {},
    onAddNewWord: () -> Unit = {},
    onDeleteWord: (Word) -> Unit = {},
    onClearWordField: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BasicTextField(
            modifier = Modifier.padding(bottom = 8.dp),
            value = word,
            placeholder = stringResource(Res.string.vocabulary_search_add),
            onValueChange = onWordChange,
            trailingIcon = {
                if (word.isNotEmpty()) {
                    IconButton(
                        onClick = onClearWordField
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (word.isNotEmpty()) {
                VocabularyContentView(
                    words = vocabulary,
                    word = word,
                    meaning = meaning,
                    notes = notes,
                    onMeaningChange = onMeaningChange,
                    onNotesChange = onNotesChange,
                    onAddNewWord = onAddNewWord,
                    onDeleteWord = onDeleteWord,
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn {
                        item {
                            VocabularyOverview(
                                totalWords = totalWords,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VocabularyContentView(
    words: List<Word>,
    word: String,
    meaning: String,
    notes: String?,
    onMeaningChange: (String) -> Unit = {},
    onNotesChange: (String) -> Unit = {},
    onAddNewWord: () -> Unit = {},
    onDeleteWord: (Word) -> Unit = {}
) {
    if (words.isEmpty()) {
        val focusManager = LocalFocusManager.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {
                item {
                    AddVocabularyView(
                        focusManager = focusManager,
                        word = word,
                        meaning = meaning,
                        notes = notes,
                        onMeaningChange = onMeaningChange,
                        onNotesChange = onNotesChange,
                        onAddNewWord = onAddNewWord
                    )
                }
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(words, key = { it.id!! }) { word ->
                WordCard(
                    word = word,
                    onDeleteClick = onDeleteWord
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
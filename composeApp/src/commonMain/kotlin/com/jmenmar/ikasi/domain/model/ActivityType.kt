package com.jmenmar.ikasi.domain.model

import androidx.compose.ui.graphics.Color
import com.jmenmar.ikasi.ui.Blue
import com.jmenmar.ikasi.ui.Green
import com.jmenmar.ikasi.ui.Orange
import com.jmenmar.ikasi.ui.Yellow
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.book
import ikasi.composeapp.generated.resources.confidence
import ikasi.composeapp.generated.resources.fluency
import ikasi.composeapp.generated.resources.headphones
import ikasi.composeapp.generated.resources.listening
import ikasi.composeapp.generated.resources.pencil
import ikasi.composeapp.generated.resources.reading
import ikasi.composeapp.generated.resources.speak
import ikasi.composeapp.generated.resources.speaking
import ikasi.composeapp.generated.resources.understanding
import ikasi.composeapp.generated.resources.vocabulary
import ikasi.composeapp.generated.resources.writing
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class ActivityType(
    val title: StringResource,
    val color: Color,
    val icon: DrawableResource,
    val skills: List<StringResource> = emptyList(),
) {
    SPEAKING(
        title = Res.string.speaking,
        color = Orange,
        icon = Res.drawable.speak,
        skills = listOf(Res.string.fluency, Res.string.confidence),
    ),
    READING(
        title = Res.string.reading,
        color = Yellow,
        icon = Res.drawable.book,
        skills = listOf(Res.string.understanding, Res.string.vocabulary),
    ),
    LISTENING(
        title = Res.string.listening,
        color = Green,
        icon = Res.drawable.headphones,
        skills = listOf(Res.string.understanding, Res.string.vocabulary),
    ),
    WRITING(
        title = Res.string.writing,
        color = Blue,
        icon = Res.drawable.pencil,
        skills = listOf(Res.string.vocabulary, Res.string.fluency),
    ),
}
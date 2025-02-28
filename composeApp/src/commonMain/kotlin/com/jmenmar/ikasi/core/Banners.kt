package com.jmenmar.ikasi.core

import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Banner

val SpeakingBanner1 = Banner(
    id = 1,
    type = ActivityType.SPEAKING,
    title = "Speaking",
    description = "You can practise speaking by having a conversation with some AI or even reflecting about something out loud."
)
val ReadingBanner1 = Banner(
    id = 1,
    type = ActivityType.READING,
    title = "Reading",
    description = "Reading increases your vocabulary and teaches you new expressions. Read blogs, books, comics, mangas..."
)
val WritingBanner1 = Banner(
    id = 1,
    type = ActivityType.WRITING,
    title = "Writing",
    description = "You can practice Journalism, reflect on your thoughts, describe how your day went or even write a story."
)
val ListeningBanner1 = Banner(
    id = 1,
    type = ActivityType.LISTENING,
    title = "Listening",
    description = "The perfect time to relax watching your favourite TV series, a film, a podcast... subtitled or not."
)

val allBanners = listOf(SpeakingBanner1, ReadingBanner1, WritingBanner1, ListeningBanner1)

package com.jmenmar.ikasi.domain.model

data class Banner(
    val id: Int,
    val type: ActivityType,
    val title: String,
    val description: String,
)

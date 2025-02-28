package com.jmenmar.ikasi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
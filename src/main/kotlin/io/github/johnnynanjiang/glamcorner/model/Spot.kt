package io.github.johnnynanjiang.glamcorner.model

enum class Spot(val symbol: String = " ") {
    EMPTY(" "),
    PLAYER("*"),
    BOT("-")
}
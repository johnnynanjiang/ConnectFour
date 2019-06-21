package io.github.johnnynanjiang.glamcorner.model

enum class Player(val symbol: String = " ") {
    EMPTY(" "),
    HUMAN_PLAYER_1("*"),
    HUMAN_PLAYER_2("#"),
    BOT_PLAYER_1("-"),
    BOT_PLAYER_2("x")
}
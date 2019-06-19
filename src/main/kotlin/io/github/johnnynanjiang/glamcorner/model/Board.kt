package io.github.johnnynanjiang.glamcorner.model

enum class Spot(val text: String = " ") {
    EMPTY(" "),
    PLAYER("*"),
    BOT("-")
}

data class Board(val column: Int, val row: Int) {
    val grid = Array(row) { Array(column) { Spot.EMPTY } }
}
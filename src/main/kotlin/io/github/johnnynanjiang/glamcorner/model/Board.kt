package io.github.johnnynanjiang.glamcorner.model

enum class Spot(val text: String = " ") {
    EMPTY(" "),
    PLAYER("*"),
    BOT("-")
}

data class Board(val row: Int, val column: Int) {
    val grid = Array(row) { Array(column) { Spot.EMPTY } }

    val minRowIndex
        get() = 0

    val maxRowIndex
        get() = row - 1

    val minColumnIndex
        get() = 0

    val maxColumnIndex
        get() = column - 1
}
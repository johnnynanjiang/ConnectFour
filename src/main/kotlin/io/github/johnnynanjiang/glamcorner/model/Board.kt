package io.github.johnnynanjiang.glamcorner.model

data class Board(val row: Int, val column: Int) {
    val grid = Array(row) { Array(column) { Player.EMPTY } }

    val minRowIndex
        get() = 0

    val maxRowIndex
        get() = row - 1

    val minColumnIndex
        get() = 0

    val maxColumnIndex
        get() = column - 1
}
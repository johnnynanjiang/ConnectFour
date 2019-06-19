package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.model.Board

class BoardView(val board: Board) {
    fun draw() {
        with(board) {
            // print rows
            for (row in (row - 1).downTo(0)) {
                // print columns
                for (col in 0 until column) {
                    print(String.format("|%s", grid[row][col].text)) // print column
                }
                println("|") // print right border
            }
        }
    }
}
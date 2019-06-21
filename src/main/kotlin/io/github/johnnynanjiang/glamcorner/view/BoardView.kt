package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.model.Board

class BoardView(val board: Board) {
    fun draw(): String {
        val output = StringBuffer()

        with(board) {
            // print rows
            for (row in (row - 1).downTo(0)) {
                // print columns
                for (col in 0 until column) {
                    // print column
                    output.append(String.format("|%s", grid[row][col].symbol))
                }
                // print right border
                output.append("|\n")
            }

            // print column indicators
            for (row in 0..row) {
                output.append(String.format(" %d", row))
            }
            output.append("\n")
        }

        return output.toString()
    }
}
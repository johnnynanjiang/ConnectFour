package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.model.Board

class BoardView(val board: Board) {
    fun draw(): String {
        val output = StringBuffer()

        with(board) {
            // print rows
            for (_row in (maxRowIndex).downTo(minRowIndex)) {
                // print columns
                for (_col in minColumnIndex..maxColumnIndex) {
                    // print column
                    output.append(String.format("|%s", grid[_row][_col].symbol))
                }
                // print right border
                output.append("|\n")
            }

            // print column indicators
            for (_col in minColumnIndex..maxColumnIndex) {
                output.append(String.format(" %d", _col))
            }
            output.append("\n")
        }

        return output.toString()
    }
}
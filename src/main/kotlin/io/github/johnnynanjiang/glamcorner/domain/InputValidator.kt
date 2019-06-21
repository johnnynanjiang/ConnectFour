package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Player

fun convertStringToInt(str: String) = str.toInt()

class InputValidator(val board: Board) {
    private companion object {
        private const val ERROR_INVALID_COLUMN = "Input should be a valid number between 0 and %s"
        private const val ERROR_COLUMN_NUMBER_OUT_OF_RANGE = "Column number out of range, should be between 0 and %s"
        private const val ERROR_COLUMN_FULL = "Column %s is full"
    }

    fun validate(input: String) =
            try {
                val col = convertStringToInt(input)
                checkIfOutOfRange(col)
                checkIfColumnFull(col)
            } catch (e: NumberFormatException) {
                throw InvalidInputException(String.format(ERROR_INVALID_COLUMN, board.maxColumnIndex))
            }

    private fun checkIfOutOfRange(col: Int) {
        if (col < board.minColumnIndex || col > board.maxColumnIndex) {
            throw InvalidInputException(String.format(ERROR_COLUMN_NUMBER_OUT_OF_RANGE, board.maxColumnIndex))
        }
    }

    private fun checkIfColumnFull(col: Int) {
        if (board.grid[board.maxRowIndex][col] != Player.EMPTY) {
            throw InvalidInputException(String.format(ERROR_COLUMN_FULL, col))
        }
    }
}
package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView
import java.lang.IllegalArgumentException
import java.lang.Math.random

class GameController {
    private companion object {
        const val MINIMUM_COLUMN_INDEX = 0
        const val MAXIMUM_COLUMN_INDEX = 6
        const val MINIMUM_ROW_INDEX = 0
        const val MAXIMUM_ROW_INDEX = 5

        const val TEXT_HELP = """
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""
        const val TEXT_QUIT = "Quit the game!"

        const val ERROR_INVALID_COLUMN = "Input should be a valid integer between 0 and %s"
        const val ERROR_COLUMN_NUMBER_OUT_OF_RANGE = "Column number out of range, should be between 0 and %s"
        const val ERROR_COLUMN_FULL = "Column %s is full"
        const val ERROR_ALL_COLUMNS_FULL = "All columns are full"
    }

    private val board = Board(column = MAXIMUM_COLUMN_INDEX + 1, row = MAXIMUM_ROW_INDEX + 1)

    fun draw() = BoardView(board = board).draw()

    fun run() {
        var quit = false

        draw()

        do {
            var input = promptForPlayerInput()

            when (input) {
                "q" -> {
                    quit = true
                    println(TEXT_QUIT)
                }
                "h" -> {
                    println(TEXT_HELP)
                }
                else -> input?.let { performAction(it) }
            }
        } while (!quit)
    }

    fun performAction(input: String) {
        val col = validate(input)
        updateBoard(col)
        draw()
        updateBoardByBot()
        draw()
    }

    fun validate(input: String): Int {
        val col = checkIfValidInt(input)
        checkIfOutOfRange(col)
        checkIfColumnFull(col)
        return col
    }

    private fun promptForPlayerInput(): String {
        println("")
        println("Waiting for command(type in 'h' for help):")

        val input = readLine()
        println("Input: $input")

        return input ?: ""
    }

    private fun checkIfValidInt(input: String): Int =
            try {
                input.toInt()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(
                        String.format(ERROR_INVALID_COLUMN, MAXIMUM_COLUMN_INDEX))
            }

    private fun checkIfOutOfRange(col: Int) {
        if (col < MINIMUM_COLUMN_INDEX || col > board.column) {
            throw IllegalArgumentException(
                    String.format(ERROR_COLUMN_NUMBER_OUT_OF_RANGE, MAXIMUM_COLUMN_INDEX))
        }
    }

    private fun checkIfColumnFull(col: Int) {
        if (board.grid[MAXIMUM_ROW_INDEX][col] != Spot.EMPTY) {
            throw IllegalArgumentException(
                    String.format(ERROR_COLUMN_FULL, col))
        }
    }

    private fun updateBoard(col: Int, isBot: Boolean = false) {
        for (row in MINIMUM_ROW_INDEX..MAXIMUM_ROW_INDEX) {
            if (board.grid[row][col] == Spot.EMPTY) {
                if (isBot) {
                    board.grid[row][col] = Spot.BOT
                } else {
                    board.grid[row][col] = Spot.PLAYER
                }

                return
            }
        }
    }

    private fun updateBoardByBot() {
        val col = pickRandomNonEmptyColumn()
        updateBoard(col, isBot = true)
        Thread.sleep(3000)
    }

    private fun getNonEmptyColumns(): List<Int> {
        val nonEmptyCols = mutableListOf<Int>()

        for (col in MINIMUM_COLUMN_INDEX..MAXIMUM_COLUMN_INDEX) {
            if (board.grid[MAXIMUM_ROW_INDEX][col] == Spot.EMPTY) {
                nonEmptyCols.add(col)
            }
        }

        return nonEmptyCols
    }

    private fun pickRandomNonEmptyColumn(): Int {
        val nonEmptyCols = getNonEmptyColumns()

        if (nonEmptyCols.isEmpty()) {
            throw IllegalArgumentException(ERROR_ALL_COLUMNS_FULL)
        } else {
            val randomIndex = (MINIMUM_COLUMN_INDEX until nonEmptyCols.size).shuffled().last()
            return nonEmptyCols[randomIndex]
        }
    }
}
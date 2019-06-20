package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.helper.Judge
import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView
import java.lang.IllegalArgumentException

class GameController {
    companion object {
        private const val TEXT_HELP = """
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""
        private const val TEXT_QUIT = "Quit the game!"
        private const val TEXT_WINNING = "Someone won the game!"

        private const val ERROR_INVALID_COLUMN = "Input should be a valid integer between 0 and %s"
        private const val ERROR_COLUMN_NUMBER_OUT_OF_RANGE = "Column number out of range, should be between 0 and %s"
        private const val ERROR_COLUMN_FULL = "Column %s is full"
        private const val ERROR_ALL_COLUMNS_FULL = "All columns are full"
    }

    private val board = Board(row = 6, column = 7)

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
                else -> input?.let { quit = performAction(it) }
            }
        } while (!quit)
    }

    private fun performAction(input: String): Boolean {
        val col = validate(input)
        dropInColumn(col)
        if (draw()) return true
        updateBoardByBot()
        if (draw()) return true
        return false
    }

    private fun validate(input: String): Int {
        val col = checkIfValidInt(input)
        checkIfOutOfRange(col)
        checkIfColumnFull(col)
        return col
    }

    private fun draw(): Boolean {
        BoardView(board = board).draw()

        val winner = Judge(board).getWinner()
        if (winner != null) {
            println(TEXT_WINNING)
            return true
        }

        return false
    }

    private fun promptForPlayerInput(): String {
        println()
        println("Waiting for command(type in 'h' for help):")

        val input = readLine()

        return input ?: ""
    }

    private fun checkIfValidInt(input: String): Int =
            try {
                input.toInt()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(
                        String.format(ERROR_INVALID_COLUMN, board.maxColumnIndex))
            }

    private fun checkIfOutOfRange(col: Int) {
        if (col < board.minColumnIndex || col > board.column) {
            throw IllegalArgumentException(
                    String.format(ERROR_COLUMN_NUMBER_OUT_OF_RANGE, board.maxColumnIndex))
        }
    }

    private fun checkIfColumnFull(col: Int) {
        if (board.grid[board.maxRowIndex][col] != Spot.EMPTY) {
            throw IllegalArgumentException(
                    String.format(ERROR_COLUMN_FULL, col))
        }
    }

    private fun dropInColumn(col: Int, isBot: Boolean = false) {
        for (row in board.minRowIndex..board.maxRowIndex) {
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
        println()
        println("Bot is thinking...")
        Thread.sleep(1000)
        val col = pickRandomNonEmptyColumn()
        dropInColumn(col, isBot = true)
    }

    private fun getNonEmptyColumns(): List<Int> {
        val nonEmptyCols = mutableListOf<Int>()

        for (col in board.minColumnIndex..board.maxColumnIndex) {
            if (board.grid[board.maxRowIndex][col] == Spot.EMPTY) {
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
            val randomIndex = (board.minColumnIndex until nonEmptyCols.size).shuffled().last()
            return nonEmptyCols[randomIndex]
        }
    }
}
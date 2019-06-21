package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.domain.InputValidator
import io.github.johnnynanjiang.glamcorner.domain.Judge
import io.github.johnnynanjiang.glamcorner.domain.convertStringToInt
import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView
import java.lang.IllegalArgumentException

class GameController {
    private companion object {
        const val TEXT_HELP =
"""
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""
        const val TEXT_QUIT = "Quit the game!"
        const val TEXT_WINNING = "Someone won the game!"

        const val ERROR_ALL_COLUMNS_FULL = "All columns are full"
    }

    private val board = Board(row = 6, column = 7)
    private val boardView = BoardView(board = board)
    private val inputValidator = InputValidator(board = board)

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
                else -> input?.let {
                    quit = performAction(it)
                }
            }
        } while (!quit)
    }

    private fun performAction(input: String): Boolean {
        inputValidator.validate(input)

        val col = convertStringToInt(input)
        dropInColumn(col)
        if (draw()) return true
        updateBoardByBot()
        if (draw()) return true
        return false
    }

    private fun draw(): Boolean {
        println(boardView.draw())

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
package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.domain.InputValidator
import io.github.johnnynanjiang.glamcorner.domain.InvalidInputException
import io.github.johnnynanjiang.glamcorner.domain.Judge
import io.github.johnnynanjiang.glamcorner.domain.convertStringToInt
import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView
import java.lang.IllegalArgumentException

class GameController {
    private companion object {
        const val MESSAGE_HELP =
"""
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""
        const val MESSAGE_QUIT = "\nQuit the game!\n"
        const val MESSAGE_WINNING = "Someone won the game!"
        const val MESSAGE_PROMPT = "Type a column number to drop in (type 'h' for help), valid columns are:"
        const val MESSAGE_BOT = "\nBot is thinking...\n"

        const val ERROR_ALL_COLUMNS_FULL = "All columns are full"

        const val COMMAND_QUIT = "q"
        const val COMMAND_HELP = "h"
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
                COMMAND_QUIT -> {
                    quit = true
                    println(MESSAGE_QUIT)
                }
                COMMAND_HELP -> {
                    println(MESSAGE_HELP)
                }
                else -> input?.let {
                    quit = executeCommand(it)
                }
            }
        } while (!quit)
    }

    private fun printInConsole(message: String) = println(message)

    private fun executeCommand(input: String): Boolean {
        var quit = false

        try {
            inputValidator.validate(input)
        } catch (e: InvalidInputException) {
            printInConsole(e.message)
            return quit
        }

        val col = convertStringToInt(input)
        dropInColumn(col)

        quit = draw()
        if (quit) return quit

        updateBoardByBot()

        quit = draw()
        if (quit) return quit

        return quit
    }

    private fun draw(): Boolean {
        var quit = false

        println(boardView.draw())

        val winner = Judge(board).getWinner()
        if (winner != null) {
            println(MESSAGE_WINNING)
            quit = true
        }

        return quit
    }

    private fun promptForPlayerInput(): String {
        val nonFullColumns = getNonFullColumns()
        println(MESSAGE_PROMPT + nonFullColumns.toString())

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
        println(MESSAGE_BOT)
        Thread.sleep(1000)
        val col = pickRandomNonFullColumn()
        dropInColumn(col, isBot = true)
    }

    private fun getNonFullColumns(): List<Int> {
        val nonEmptyCols = mutableListOf<Int>()

        for (col in board.minColumnIndex..board.maxColumnIndex) {
            if (board.grid[board.maxRowIndex][col] == Spot.EMPTY) {
                nonEmptyCols.add(col)
            }
        }

        return nonEmptyCols
    }

    private fun pickRandomNonFullColumn(): Int {
        val nonFullCols = getNonFullColumns()

        if (nonFullCols.isEmpty()) {
            throw IllegalArgumentException(ERROR_ALL_COLUMNS_FULL)
        } else {
            val randomIndex = (board.minColumnIndex until nonFullCols.size).shuffled().last()
            return nonFullCols[randomIndex]
        }
    }
}
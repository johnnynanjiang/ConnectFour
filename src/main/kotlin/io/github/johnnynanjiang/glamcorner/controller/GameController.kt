package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.domain.*
import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView

class GameController(val board: Board,
                     val boardManager: BoardManager,
                     val inputValidator: InputValidator,
                     val players: List<Player>) {
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

        const val COMMAND_QUIT = "q"
        const val COMMAND_HELP = "h"
    }

    private val boardView = BoardView(board = board)
    private var playerToken = 0

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
            quit = false
            return quit
        } catch (e: InvalidBoardStatusException) {
            printInConsole(e.message)
            quit = true
            return quit
        }

        val col = convertStringToInt(input)
        boardManager.dropInColumn(col)

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
        val nonFullColumns = boardManager.getAvailableColumns()
        println(MESSAGE_PROMPT + nonFullColumns.toString())

        val input = readLine()

        return input ?: ""
    }

    private fun updateBoardByBot() {
        println(MESSAGE_BOT)
        Thread.sleep(1000)
        val col = boardManager.pickARandomAvailableColumn()
        boardManager.dropInColumn(col, isBot = true)
    }
}
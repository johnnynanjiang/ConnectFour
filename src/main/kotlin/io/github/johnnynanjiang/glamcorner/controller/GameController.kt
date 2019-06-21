package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.domain.*
import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.BoardView

class GameController(val board: Board,
                     val boardManager: BoardManager,
                     val inputValidator: InputValidator,
                     val players: List<Player>) {
    companion object {
        private const val MESSAGE_QUIT = "\nQuit the game!\n"
        private const val MESSAGE_WINNING = "Congrats player [%s] for winning the game!"
        private const val MESSAGE_PROMPT = "Hi player [%s], please type a column number to drop in (type 'h' for help), valid columns are:"
        private const val MESSAGE_HELP =
                """
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""

        const val COMMAND_HELP = "h"
        const val COMMAND_QUIT = "q"
    }

    private val boardView = BoardView(board = board)
    private var playerToken = 0

    fun run() {
        var quit = false

        draw()

        do {
            promptForInput()
            var input = getInputFromPlayer()

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
        boardManager.dropInColumnForPlayer(col, getPlayerWithToken())

        quit = draw()
        if (quit) return quit

        passTokenToNextPlayer()

        return quit
    }

    private fun getPlayerWithToken() = players[playerToken]

    private fun passTokenToNextPlayer() {
        playerToken = ++playerToken % players.size
    }

    private fun draw(): Boolean {
        var quit = false

        println(boardView.draw())

        val winner = Judge(board).getWinner()
        if (winner != null) {
            println(String.format(MESSAGE_WINNING, getPlayerWithToken().symbol))
            quit = true
        }

        return quit
    }

    private fun promptForInput() {
        val nonFullColumns = boardManager.getAvailableColumns()
        println(String.format(MESSAGE_PROMPT, getPlayerWithToken().symbol) + nonFullColumns.toString())
    }

    private fun getInputFromPlayer(): String = getPlayerWithToken().getInput()
}
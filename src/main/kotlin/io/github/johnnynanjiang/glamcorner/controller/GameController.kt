package io.github.johnnynanjiang.glamcorner.controller

import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.GameView

class GameController {
    private companion object {
        const val HELP_TEXT = """
- type in column number straight away, e.g. 1
- type q to quit the game
- type h for this help
"""
    }

    private val board = Board(column = 7, row = 6)
    private val humanPlayer = HumanPlayer(dots = listOf(Dot(0, 0), Dot(0, 1)))
    private val botPlayer = BotPlayer(dots = listOf(Dot(1, 1), Dot(1, 2)))
    private val gameState = GameState(board = board, humanPlayer = humanPlayer, botPlayer = botPlayer)

    fun draw() = GameView(gameState = gameState).draw()

    fun run() {
        var active = true

        do {
            draw()

            println("")
            println("Waiting for command(type in 'h' for help):")
            val input = readLine()

            when (input) {
                "q" -> {
                    active = false
                    println("Quit the game")
                }
                "h" -> {
                    println(HELP_TEXT)
                }
            }
        } while (active)
    }
}
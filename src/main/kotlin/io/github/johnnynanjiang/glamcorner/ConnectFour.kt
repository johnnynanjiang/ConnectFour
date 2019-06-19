package io.github.johnnynanjiang.glamcorner

import io.github.johnnynanjiang.glamcorner.model.*
import io.github.johnnynanjiang.glamcorner.view.GameView

class ConnectFour {
    private val board = Board(column = 7, row = 6)
    private val humanPlayer = HumanPlayer(dots = listOf(Dot(0, 0), Dot(0, 1)))
    private val botPlayer = BotPlayer(dots = listOf(Dot(1, 1), Dot(1, 2)))
    private val gameState = GameState(board = board, humanPlayer = humanPlayer, botPlayer = botPlayer)

    fun draw() = GameView(gameState = gameState).draw()
}

fun main(vararg args: String) {
    val connectFour = ConnectFour()
    connectFour.draw()
}
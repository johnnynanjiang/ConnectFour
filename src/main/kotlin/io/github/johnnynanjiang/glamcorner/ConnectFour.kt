package io.github.johnnynanjiang.glamcorner

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.BotPlayer
import io.github.johnnynanjiang.glamcorner.model.GameState
import io.github.johnnynanjiang.glamcorner.model.HumanPlayer
import io.github.johnnynanjiang.glamcorner.view.GameView

class ConnectFour {
    private val board = Board(column = 7, row = 6)
    private val humanPlayer = HumanPlayer(dots = emptyList())
    private val botPlayer = BotPlayer(dots = emptyList())
    private val gameState = GameState(board = board, player = humanPlayer, bot = botPlayer)

    fun draw() = GameView(gameState = gameState).draw()
}

fun main(vararg args: String) {
    val connectFour = ConnectFour()
    connectFour.draw()
}
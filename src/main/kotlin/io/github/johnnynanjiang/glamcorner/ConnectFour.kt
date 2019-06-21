package io.github.johnnynanjiang.glamcorner

import io.github.johnnynanjiang.glamcorner.controller.GameController
import io.github.johnnynanjiang.glamcorner.domain.BoardManager
import io.github.johnnynanjiang.glamcorner.domain.InputValidator
import io.github.johnnynanjiang.glamcorner.model.Player
import io.github.johnnynanjiang.glamcorner.model.Board

class ConnectFour {
    fun run() {
        val board = Board(row = 6, column = 7)
        val boardManager = BoardManager(board = board)
        val inputValidator = InputValidator(board)
        val players = listOf(Player.HUMAN_PLAYER_1, Player.BOT_PLAYER_1)

        // TODO: DI could kick in here, leave it for now for the sake of simplicity and minimum dependency
        GameController(
                board = board,
                boardManager = boardManager,
                inputValidator = inputValidator,
                players = players
        ).run()
    }
}

fun main(vararg args: String) {
    ConnectFour().run()
}
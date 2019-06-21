package io.github.johnnynanjiang.glamcorner

import io.github.johnnynanjiang.glamcorner.controller.GameController
import io.github.johnnynanjiang.glamcorner.domain.*
import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Player

class ConnectFour {
    fun run() {
        val board = Board(row = 6, column = 7)
        val boardManager = BoardManager(board = board)
        val inputValidator = InputValidator(board)

        val HUMAN_PLAYER_1 = Player("*", HumanPlayerStrategy())
        val HUMAN_PLAYER_2 = Player("#", HumanPlayerStrategy())
        val BOT_PLAYER_1 = Player("_", EasyBotPlayerStrategy(boardManager = boardManager))
        val BOT_PLAYER_2 = Player("x", HardBotPlayerStrategy(boardManager = boardManager))

        val playersForNormalMode = listOf(HUMAN_PLAYER_1, BOT_PLAYER_1)
        val playersForBothHumans = listOf(HUMAN_PLAYER_1, HUMAN_PLAYER_2)
        val playersForBothBots = listOf(BOT_PLAYER_1, BOT_PLAYER_2)

        // TODO: DI could kick in here, leave it for now for the sake of simplicity and minimum dependency
        GameController(
                board = board,
                boardManager = boardManager,
                inputValidator = inputValidator,
                players = playersForNormalMode
        ).run()
    }
}

fun main(vararg args: String) {
    ConnectFour().run()
}
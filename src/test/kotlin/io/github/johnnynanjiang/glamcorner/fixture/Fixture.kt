package io.github.johnnynanjiang.glamcorner.fixture

import io.github.johnnynanjiang.glamcorner.domain.BoardManager
import io.github.johnnynanjiang.glamcorner.domain.EasyBotPlayerStrategy
import io.github.johnnynanjiang.glamcorner.domain.HumanPlayerStrategy
import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Player
import io.github.johnnynanjiang.glamcorner.view.BoardView

val TEST_HUMAN_PLAYER = Player("*", HumanPlayerStrategy())
val TEST_BOT_PLAYER = Player("-", EasyBotPlayerStrategy(boardManager = BoardManager(buildBoard())))

fun buildBoard(): Board {
    val board = Board(6, 7)
    val grid = board.grid

    grid[0][0] = TEST_HUMAN_PLAYER
    grid[0][3] = TEST_BOT_PLAYER
    grid[0][5] = TEST_BOT_PLAYER
    grid[1][1] = TEST_BOT_PLAYER
    grid[1][4] = TEST_BOT_PLAYER
    grid[1][5] = TEST_BOT_PLAYER
    grid[1][6] = TEST_BOT_PLAYER
    grid[2][0] = TEST_HUMAN_PLAYER
    grid[2][1] = TEST_HUMAN_PLAYER
    grid[2][2] = TEST_HUMAN_PLAYER
    grid[2][3] = TEST_HUMAN_PLAYER
    grid[2][4] = TEST_HUMAN_PLAYER
    grid[2][5] = TEST_BOT_PLAYER
    grid[3][0] = TEST_BOT_PLAYER
    grid[3][1] = TEST_HUMAN_PLAYER
    grid[3][4] = TEST_BOT_PLAYER
    grid[3][5] = TEST_BOT_PLAYER
    grid[3][6] = TEST_BOT_PLAYER
    grid[4][2] = TEST_HUMAN_PLAYER
    grid[4][3] = TEST_BOT_PLAYER
    grid[5][2] = TEST_BOT_PLAYER

    return board
}

fun buildBoardView() = BoardView(buildBoard())
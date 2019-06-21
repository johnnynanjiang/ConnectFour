package io.github.johnnynanjiang.glamcorner.fixture

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Player
import io.github.johnnynanjiang.glamcorner.view.BoardView

fun buildBoard(): Board {
    val board = Board(6, 7)
    val grid = board.grid

    grid[0][0] = Player.HUMAN_PLAYER_1
    grid[0][3] = Player.BOT_PLAYER_1
    grid[0][5] = Player.BOT_PLAYER_1
    grid[1][1] = Player.BOT_PLAYER_1
    grid[1][4] = Player.BOT_PLAYER_1
    grid[1][5] = Player.BOT_PLAYER_1
    grid[1][6] = Player.BOT_PLAYER_1
    grid[2][0] = Player.HUMAN_PLAYER_1
    grid[2][1] = Player.HUMAN_PLAYER_1
    grid[2][2] = Player.HUMAN_PLAYER_1
    grid[2][3] = Player.HUMAN_PLAYER_1
    grid[2][4] = Player.HUMAN_PLAYER_1
    grid[2][5] = Player.BOT_PLAYER_1
    grid[3][0] = Player.BOT_PLAYER_1
    grid[3][1] = Player.HUMAN_PLAYER_1
    grid[3][4] = Player.BOT_PLAYER_1
    grid[3][5] = Player.BOT_PLAYER_1
    grid[3][6] = Player.BOT_PLAYER_1
    grid[4][2] = Player.HUMAN_PLAYER_1
    grid[4][3] = Player.BOT_PLAYER_1
    grid[5][2] = Player.BOT_PLAYER_1

    return board
}

fun buildBoardView() = BoardView(buildBoard())
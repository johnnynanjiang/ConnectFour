package io.github.johnnynanjiang.glamcorner.fixture

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Spot
import io.github.johnnynanjiang.glamcorner.view.BoardView

fun buildBoard(): Board {
    val board = Board(6, 7)
    val grid = board.grid

    grid[0][0] = Spot.PLAYER
    grid[0][3] = Spot.BOT
    grid[0][5] = Spot.BOT
    grid[1][1] = Spot.BOT
    grid[1][4] = Spot.BOT
    grid[1][5] = Spot.BOT
    grid[1][6] = Spot.BOT
    grid[2][0] = Spot.PLAYER
    grid[2][1] = Spot.PLAYER
    grid[2][2] = Spot.PLAYER
    grid[2][3] = Spot.PLAYER
    grid[2][4] = Spot.PLAYER
    grid[2][5] = Spot.BOT
    grid[3][0] = Spot.BOT
    grid[3][1] = Spot.PLAYER
    grid[3][4] = Spot.BOT
    grid[3][5] = Spot.BOT
    grid[3][6] = Spot.BOT
    grid[4][2] = Spot.PLAYER
    grid[4][3] = Spot.BOT
    grid[5][2] = Spot.BOT

    return board
}

fun buildBoardView() = BoardView(buildBoard())
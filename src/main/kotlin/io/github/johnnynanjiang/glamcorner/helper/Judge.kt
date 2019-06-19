package io.github.johnnynanjiang.glamcorner.helper

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Spot

class Judge(val board: Board) {
    fun judgeWhoWins() {
        for (row in board.minRowIndex..board.maxRowIndex) {
            for (col in board.minColumnIndex..board.maxColumnIndex) {
                val currentSpot = board.grid[row][col]

            }
        }
    }

    fun canSpotWin(spot: Spot) {

    }
}
package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Player

class BoardManager(val board: Board) {
    private companion object {
        const val ERROR_ALL_COLUMNS_FULL = "All columns are full"
    }

    fun dropInColumn(col: Int, isBot: Boolean = false) {
        for (row in board.minRowIndex..board.maxRowIndex) {
            if (board.grid[row][col] == Player.EMPTY) {
                if (isBot) {
                    board.grid[row][col] = Player.BOT_PLAYER_1
                } else {
                    board.grid[row][col] = Player.HUMAN_PLAYER_1
                }
                return
            }
        }
    }

    fun getAvailableColumns(): List<Int> {
        val availableCols = mutableListOf<Int>()

        for (col in board.minColumnIndex..board.maxColumnIndex) {
            if (board.grid[board.maxRowIndex][col] == Player.EMPTY) {
                availableCols.add(col)
            }
        }

        return availableCols
    }

    fun pickARandomAvailableColumn(): Int {
        val availableCols = getAvailableColumns()

        if (availableCols.isEmpty()) {
            throw InvalidBoardStatusException(ERROR_ALL_COLUMNS_FULL)
        } else {
            val randomIndex = (board.minColumnIndex until availableCols.size).shuffled().last()
            return availableCols[randomIndex]
        }
    }
}
package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.EMPTY_PLAYER
import io.github.johnnynanjiang.glamcorner.model.Player

class BoardManager(val board: Board) {
    private companion object {
        const val ERROR_ALL_COLUMNS_FULL = "All columns are full"
    }

    fun dropInColumnForPlayer(col: Int, player: Player) {
        for (row in board.minRowIndex..board.maxRowIndex) {
            if (board.grid[row][col] == EMPTY_PLAYER) {
                board.grid[row][col] = player
                return
            }
        }
    }

    fun getAvailableColumns(): List<Int> {
        val availableCols = mutableListOf<Int>()

        for (col in board.minColumnIndex..board.maxColumnIndex) {
            if (board.grid[board.maxRowIndex][col] == EMPTY_PLAYER) {
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

    fun pickAColumnThatWillKillTheOpponent(): Int {
        return (board.minColumnIndex until board.maxColumnIndex).shuffled().last()
    }
}
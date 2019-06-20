package io.github.johnnynanjiang.glamcorner.helper

import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Spot

data class Coordinate(val row: Int, val col: Int, val spot: Spot = Spot.EMPTY)
data class Offset(val row: Int, val col: Int)
enum class Direction(val offsetUp: Offset, val offsetDown: Offset) {
    HORIZONTAL(offsetUp = Offset(row = 0, col = -1), offsetDown = Offset(row = 0, col = 1)),
    VERTICAL(offsetUp = Offset(row = 1, col = 0), offsetDown = Offset(row = -1, col = 0)),
    BACKWARD_SLASH(offsetUp = Offset(row = 1, col = -1), offsetDown = Offset(row = -1, col = 1)),
    FORWARD_SLASH(offsetUp = Offset(row = 1, col = 1), offsetDown = Offset(row = -1, col = -1))
}

class Judge(val board: Board) {
    companion object {
        const val WINNING_NUMBER = 4
    }

    fun judgeWhoWins() {
        for (row in board.minRowIndex..board.maxRowIndex) {
            for (col in board.minColumnIndex..board.maxColumnIndex) {
                val currentCoordinate = Coordinate(row, col, board.grid[row][col])

            }
        }
    }

    fun canCoordinateWin(coordinate: Coordinate) {

    }

    fun getNumberInDirection(coordinate: Coordinate, direction: Direction): Int {
        var total = 1

        for (offset in listOf(direction.offsetUp, direction.offsetDown)) {
            var nextCoordinate = getNextCoordinate(coordinate, offset)
            while (nextCoordinate != null) {
                if (nextCoordinate.spot == coordinate.spot) {
                    ++total
                    nextCoordinate = getNextCoordinate(nextCoordinate, offset)
                } else {
                    break
                }
            }
        }

        return total
    }

    fun getNextCoordinate(coordinate: Coordinate, offset: Offset): Coordinate? {
        val nextRowIndex = coordinate.row + offset.row
        val nextColIndex = coordinate.col + offset.col

        if (nextRowIndex < board.minRowIndex || nextRowIndex > board.maxRowIndex) return null
        if (nextColIndex < board.minRowIndex || nextColIndex > board.maxRowIndex) return null

        return Coordinate(nextRowIndex, nextColIndex, board.grid[nextRowIndex][nextColIndex])
    }
}
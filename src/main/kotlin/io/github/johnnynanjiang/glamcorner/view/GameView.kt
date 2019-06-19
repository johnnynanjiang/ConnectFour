package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.model.GameState
import io.github.johnnynanjiang.glamcorner.model.Dot

class GameView(val gameState: GameState) {
    fun draw() {
        with(gameState) {
            // print rows
            for (row in (board.row-1).downTo(0) ) {
                // print columns
                for (col in 0 until board.column) {
                    val dot = Dot(row, col)
                    val dotToDraw = when {
                        humanPlayer.hasDot(dot) -> humanPlayer.dotToDraw()
                        botPlayer.hasDot(dot) -> botPlayer.dotToDraw()
                        else -> " "
                    }
                    print(String.format("|%s", dotToDraw)) // print column
                }
                println("|") // print right border
            }
        }
    }
}
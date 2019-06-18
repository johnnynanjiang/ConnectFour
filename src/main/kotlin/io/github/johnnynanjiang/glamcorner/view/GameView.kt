package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.model.GameState

class GameView(val gameState: GameState) {
    fun draw() {
        // print rows
        for (row in 1..gameState.board.row) {
            // print columns
            for (col in 1..gameState.board.column) {
                print(String.format("|%s", " ")) // print column
            }
            println("|") // print right border
        }
    }
}
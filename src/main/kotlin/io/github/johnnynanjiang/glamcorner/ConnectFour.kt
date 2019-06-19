package io.github.johnnynanjiang.glamcorner

import io.github.johnnynanjiang.glamcorner.controller.GameController

class ConnectFour {
    fun run() = GameController().run()
}

fun main(vararg args: String) {
    ConnectFour().run()
}
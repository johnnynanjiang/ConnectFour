package io.github.johnnynanjiang.glamcorner.model

data class Dot(val x: Int, val y: Int)

abstract class Player(open val dots: List<Dot>) {
    abstract fun dotToDraw(): String

    fun hasDot(dot: Dot): Boolean = dots.contains(dot)
}

class HumanPlayer(override val dots: List<Dot>) : Player(dots) {
    override fun dotToDraw(): String = "*"
}

class BotPlayer(override val dots: List<Dot>) : Player(dots) {
    override fun dotToDraw(): String = "-"
}
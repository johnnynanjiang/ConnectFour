package io.github.johnnynanjiang.glamcorner.model

data class Dot(val x: Int, val y: Int)

abstract class Player(open val dots: List<Dot>) {
    abstract fun drawDot(): String
}

class HumanPlayer(override val dots: List<Dot>) : Player(dots) {
    override fun drawDot(): String = "*"
}

class BotPlayer(override val dots: List<Dot>) : Player(dots) {
    override fun drawDot(): String = "-"
}
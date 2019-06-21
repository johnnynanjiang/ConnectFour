package io.github.johnnynanjiang.glamcorner.model

import io.github.johnnynanjiang.glamcorner.domain.*

val EMPTY_PLAYER = Player()

class Player(val symbol: String = " ",
             private val strategy: Strategy = NoStrategy()) {
    override fun equals(other: Any?): Boolean =
            other?.let {
                if (other is Player) {
                    this.symbol == other.symbol
                } else {
                    false
                }
            } ?: false

    fun getInput() = strategy.getNextMove()
}
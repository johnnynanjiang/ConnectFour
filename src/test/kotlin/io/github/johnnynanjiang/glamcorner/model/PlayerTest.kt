package io.github.johnnynanjiang.glamcorner.model

import io.github.johnnynanjiang.glamcorner.domain.HumanPlayerStrategy
import io.github.johnnynanjiang.glamcorner.domain.NoStrategy
import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    @Test
    fun playersWithSameSymbolShouldBeEqual() {
        val player1 = Player("symbol", NoStrategy())
        val player2 = Player("symbol", HumanPlayerStrategy())

        assertTrue(player1 == player2)
    }
}
package io.github.johnnynanjiang.glamcorner.model

import org.junit.Assert.*
import org.junit.Test

class SpotTest {
    @Test
    fun shouldHaveCorrectSymbols() {
        assertEquals(" ", Spot.EMPTY.symbol)
        assertEquals("*", Spot.PLAYER.symbol)
        assertEquals("-", Spot.BOT.symbol)
    }
}
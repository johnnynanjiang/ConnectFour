package io.github.johnnynanjiang.glamcorner.model

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    @Test
    fun shouldHaveCorrectSymbols() {
        assertEquals(" ", Player.EMPTY.symbol)
        assertEquals("*", Player.HUMAN_PLAYER_1.symbol)
        assertEquals("#", Player.HUMAN_PLAYER_2.symbol)
        assertEquals("-", Player.BOT_PLAYER_1.symbol)
        assertEquals("x", Player.BOT_PLAYER_2.symbol)
    }
}
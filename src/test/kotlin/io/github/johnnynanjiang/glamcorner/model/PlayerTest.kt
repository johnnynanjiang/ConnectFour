package io.github.johnnynanjiang.glamcorner.model

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

internal class PlayerTest {
    @Test
    fun hasDot() {
        assertTrue(HumanPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 0)))
        assertFalse(HumanPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 1)))

        assertTrue(BotPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 0)))
        assertFalse(BotPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 1)))
    }
}
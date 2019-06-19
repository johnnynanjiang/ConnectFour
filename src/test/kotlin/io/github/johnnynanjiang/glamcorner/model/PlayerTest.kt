package io.github.johnnynanjiang.glamcorner.model

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {
    @Test
    fun hasDot() {
        assertTrue(HumanPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 0)))
        assertFalse(HumanPlayer(listOf(Dot(0, 0), Dot(1, 1))).hasDot(Dot(0, 1)))
    }
}
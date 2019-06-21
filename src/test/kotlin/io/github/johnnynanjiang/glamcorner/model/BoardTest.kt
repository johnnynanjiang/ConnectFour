package io.github.johnnynanjiang.glamcorner.model

import io.github.johnnynanjiang.glamcorner.fixture.buildBoard
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BoardTest {
    lateinit var board: Board

    @Before
    fun setUp() {
        board = buildBoard()
    }

    @Test
    fun boardShouldHaveCorrectBoundaryIndexes() {
        assertEquals(0, board.minRowIndex)
        assertEquals(5, board.maxRowIndex)
        assertEquals(0, board.minColumnIndex)
        assertEquals(6, board.maxColumnIndex)
    }

    @Test
    fun boardShouldHaveCorrectDimension() {
        assertEquals(6, board.grid.size)
        assertEquals(7, board.grid[0].size)
    }

    @Test
    fun boardShouldHaveCorrectDefaultSpotValue() {
        assertEquals(Spot.EMPTY, board.grid[5][0])
    }
}
package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.fixture.buildBoard
import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.Spot
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JudgeTest {
    lateinit var board: Board
    lateinit var judge: Judge

    @Before
    fun setUp() {
        board = buildBoard()
        judge = Judge(board = board)
    }

    @Test
    fun getNextCoordinate() {
        assertEquals(Coordinate(2, 1, Spot.PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.HORIZONTAL.offsetUp))
        assertEquals(Coordinate(2, 3, Spot.PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.HORIZONTAL.offsetDown))
        assertEquals(Coordinate(3, 1, Spot.PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.BACKWARD_SLASH.offsetUp))
        assertEquals(Coordinate(1, 3, Spot.EMPTY), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.BACKWARD_SLASH.offsetDown))
        assertEquals(Coordinate(3, 2, Spot.EMPTY), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.VERTICAL.offsetUp))
        assertEquals(Coordinate(1, 2, Spot.EMPTY), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.VERTICAL.offsetDown))
        assertEquals(Coordinate(3, 3, Spot.EMPTY), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.FORWARD_SLASH.offsetUp))
        assertEquals(Coordinate(1, 1, Spot.BOT), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.FORWARD_SLASH.offsetDown))

        assertNull(judge.getNextCoordinateByOffset(Coordinate(5, 0, Spot.PLAYER), Direction.VERTICAL.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, Spot.PLAYER), Direction.VERTICAL.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, Spot.PLAYER), Direction.FORWARD_SLASH.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, Spot.PLAYER), Direction.FORWARD_SLASH.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, Spot.PLAYER), Direction.HORIZONTAL.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, Spot.PLAYER), Direction.HORIZONTAL.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, Spot.PLAYER), Direction.BACKWARD_SLASH.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, Spot.PLAYER), Direction.BACKWARD_SLASH.offsetDown))
    }

    @Test
    fun getNumberOfSpotsInDirection() {
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = Spot.BOT), direction = Direction.HORIZONTAL))
        assertEquals(4, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = Spot.BOT), direction = Direction.VERTICAL))
        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = Spot.BOT), direction = Direction.BACKWARD_SLASH))
        assertEquals(4, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = Spot.BOT), direction = Direction.FORWARD_SLASH))

        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 0, spot = Spot.PLAYER), direction = Direction.HORIZONTAL))
        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = Spot.PLAYER), direction = Direction.HORIZONTAL))
        assertEquals(2, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = Spot.PLAYER), direction = Direction.BACKWARD_SLASH))
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = Spot.PLAYER), direction = Direction.VERTICAL))
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = Spot.PLAYER), direction = Direction.FORWARD_SLASH))
    }

    @Test
    fun canCoordinateWin() {
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 0, spot = Spot.PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 1, spot = Spot.PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 2, spot = Spot.PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 3, spot = Spot.PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 4, spot = Spot.PLAYER)))

        assertFalse(judge.canCoordinateWin(Coordinate(row = 0, col = 0, spot = Spot.PLAYER)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 3, col = 1, spot = Spot.PLAYER)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 4, col = 2, spot = Spot.PLAYER)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 0, col = 5, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 5, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 5, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 5, spot = Spot.BOT)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 0, col = 3, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 4, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 6, spot = Spot.BOT)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 6, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 4, spot = Spot.BOT)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 4, col = 3, spot = Spot.BOT)))

        assertFalse(judge.canCoordinateWin(Coordinate(row = 3, col = 0, spot = Spot.BOT)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 1, col = 1, spot = Spot.BOT)))
    }

    @Test
    fun emptySpotsShouldNeverWin() {
        assertFalse(judge.canCoordinateWin(Coordinate(row = 5, col = 0, spot = Spot.EMPTY)))
    }
}
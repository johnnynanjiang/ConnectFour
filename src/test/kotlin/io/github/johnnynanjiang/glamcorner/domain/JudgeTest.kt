package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.fixture.TEST_BOT_PLAYER
import io.github.johnnynanjiang.glamcorner.fixture.TEST_HUMAN_PLAYER
import io.github.johnnynanjiang.glamcorner.fixture.buildBoard
import io.github.johnnynanjiang.glamcorner.model.Board
import io.github.johnnynanjiang.glamcorner.model.EMPTY_PLAYER
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
        assertEquals(Coordinate(2, 1, TEST_HUMAN_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.HORIZONTAL.offsetUp))
        assertEquals(Coordinate(2, 3, TEST_HUMAN_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.HORIZONTAL.offsetDown))
        assertEquals(Coordinate(3, 1, TEST_HUMAN_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.BACKWARD_SLASH.offsetUp))
        assertEquals(Coordinate(1, 3, EMPTY_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.BACKWARD_SLASH.offsetDown))
        assertEquals(Coordinate(3, 2, EMPTY_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.VERTICAL.offsetUp))
        assertEquals(Coordinate(1, 2, EMPTY_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.VERTICAL.offsetDown))
        assertEquals(Coordinate(3, 3, EMPTY_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.FORWARD_SLASH.offsetUp))
        assertEquals(Coordinate(1, 1, TEST_BOT_PLAYER), judge.getNextCoordinateByOffset(Coordinate(2, 2), Direction.FORWARD_SLASH.offsetDown))

        assertNull(judge.getNextCoordinateByOffset(Coordinate(5, 0, TEST_HUMAN_PLAYER), Direction.VERTICAL.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, TEST_HUMAN_PLAYER), Direction.VERTICAL.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, TEST_HUMAN_PLAYER), Direction.FORWARD_SLASH.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, TEST_HUMAN_PLAYER), Direction.FORWARD_SLASH.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, TEST_HUMAN_PLAYER), Direction.HORIZONTAL.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 6, TEST_HUMAN_PLAYER), Direction.HORIZONTAL.offsetDown))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, TEST_HUMAN_PLAYER), Direction.BACKWARD_SLASH.offsetUp))
        assertNull(judge.getNextCoordinateByOffset(Coordinate(0, 0, TEST_HUMAN_PLAYER), Direction.BACKWARD_SLASH.offsetDown))
    }

    @Test
    fun getNumberOfSpotsInDirection() {
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = TEST_BOT_PLAYER), direction = Direction.HORIZONTAL))
        assertEquals(4, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = TEST_BOT_PLAYER), direction = Direction.VERTICAL))
        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = TEST_BOT_PLAYER), direction = Direction.BACKWARD_SLASH))
        assertEquals(4, judge.getNumberInDirection(Coordinate(row = 2, col = 5, spot = TEST_BOT_PLAYER), direction = Direction.FORWARD_SLASH))

        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 0, spot = TEST_HUMAN_PLAYER), direction = Direction.HORIZONTAL))
        assertEquals(5, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = TEST_HUMAN_PLAYER), direction = Direction.HORIZONTAL))
        assertEquals(2, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = TEST_HUMAN_PLAYER), direction = Direction.BACKWARD_SLASH))
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = TEST_HUMAN_PLAYER), direction = Direction.VERTICAL))
        assertEquals(1, judge.getNumberInDirection(Coordinate(row = 2, col = 2, spot = TEST_HUMAN_PLAYER), direction = Direction.FORWARD_SLASH))
    }

    @Test
    fun canCoordinateWin() {
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 0, spot = TEST_HUMAN_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 1, spot = TEST_HUMAN_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 2, spot = TEST_HUMAN_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 3, spot = TEST_HUMAN_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 4, spot = TEST_HUMAN_PLAYER)))

        assertFalse(judge.canCoordinateWin(Coordinate(row = 0, col = 0, spot = TEST_HUMAN_PLAYER)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 3, col = 1, spot = TEST_HUMAN_PLAYER)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 4, col = 2, spot = TEST_HUMAN_PLAYER)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 0, col = 5, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 5, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 2, col = 5, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 5, spot = TEST_BOT_PLAYER)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 0, col = 3, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 4, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 6, spot = TEST_BOT_PLAYER)))

        assertTrue(judge.canCoordinateWin(Coordinate(row = 1, col = 6, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 3, col = 4, spot = TEST_BOT_PLAYER)))
        assertTrue(judge.canCoordinateWin(Coordinate(row = 4, col = 3, spot = TEST_BOT_PLAYER)))

        assertFalse(judge.canCoordinateWin(Coordinate(row = 3, col = 0, spot = TEST_BOT_PLAYER)))
        assertFalse(judge.canCoordinateWin(Coordinate(row = 1, col = 1, spot = TEST_BOT_PLAYER)))
    }

    @Test
    fun emptySpotsShouldNeverWin() {
        assertFalse(judge.canCoordinateWin(Coordinate(row = 5, col = 0, spot = EMPTY_PLAYER)))
    }
}
package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.fixture.TEST_HUMAN_PLAYER
import io.github.johnnynanjiang.glamcorner.fixture.buildBoard
import io.github.johnnynanjiang.glamcorner.model.EMPTY_PLAYER
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BoardManagerTest {
    lateinit var boardManager: BoardManager

    @Before
    fun setUp() {
        boardManager = BoardManager(buildBoard())
    }

    @Test
    fun shouldBeAbleToGetAvailableColumnsThatAreNotFull() {
        assertEquals(6, boardManager.getAvailableColumns().size)

        assertEquals(0, boardManager.getAvailableColumns()[0])
        assertEquals(1, boardManager.getAvailableColumns()[1])
        assertEquals(3, boardManager.getAvailableColumns()[2])
        assertEquals(4, boardManager.getAvailableColumns()[3])
        assertEquals(5, boardManager.getAvailableColumns()[4])
        assertEquals(6, boardManager.getAvailableColumns()[5])
    }

    @Test
    fun shouldBeAbleToPickAnAvailableColumnRandomly() {
        val randomCol = boardManager.pickARandomAvailableColumn()
        assertTrue(randomCol != 2 && randomCol >= 0 && randomCol <= 6)
    }

    @Test
    fun shouldBeAbleToDropInAColumnForAPlayer() {
        assertEquals(EMPTY_PLAYER, boardManager.board.grid[4][5])

        boardManager.dropInColumnForPlayer(5, TEST_HUMAN_PLAYER)

        assertEquals(TEST_HUMAN_PLAYER, boardManager.board.grid[4][5])
    }
}
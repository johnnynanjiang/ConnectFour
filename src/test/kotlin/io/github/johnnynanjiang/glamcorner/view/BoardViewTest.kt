package io.github.johnnynanjiang.glamcorner.view

import io.github.johnnynanjiang.glamcorner.fixture.buildBoardView
import org.junit.Assert.*
import org.junit.Test

class BoardViewTest {
    @Test
    fun drawBoardInText() {
        val boardView = buildBoardView()
        assertEquals(
"""
| | |-| | | | |
| | |*|-| | | |
|-|*| | |-|-|-|
|*|*|*|*|*|-| |
| |-| | |-|-|-|
|*| | |-| |-| |
 0 1 2 3 4 5 6
""".trimStart(), boardView.draw())
    }
}
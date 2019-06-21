package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.controller.GameController

interface Strategy {
    fun getNextMove(): String
}

class NoStrategy : Strategy {
    override fun getNextMove(): String = GameController.COMMAND_QUIT
}

class HumanPlayerStrategy : Strategy {
    override fun getNextMove(): String = readLine() ?: GameController.COMMAND_HELP
}

class EasyBotPlayerStrategy(private val boardManager: BoardManager) : Strategy {
    override fun getNextMove(): String {
        // Simulate a delay for input
        Thread.sleep(1000)
        return boardManager.pickARandomAvailableColumn().toString()
    }
}

class HardBotPlayerStrategy(private val boardManager: BoardManager) : Strategy {
    override fun getNextMove(): String = boardManager.pickAColumnThatWillKillTheOpponent().toString()
}

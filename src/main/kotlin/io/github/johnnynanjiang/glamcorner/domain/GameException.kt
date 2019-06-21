package io.github.johnnynanjiang.glamcorner.domain

import java.lang.RuntimeException

open class GameException(override val message: String) : RuntimeException(message)

class InvalidInputException(override val message: String) : GameException(message)

class InvalidBoardStatusException(override val message: String) : GameException(message)
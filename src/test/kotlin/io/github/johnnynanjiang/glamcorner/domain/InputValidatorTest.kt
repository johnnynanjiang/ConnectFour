package io.github.johnnynanjiang.glamcorner.domain

import io.github.johnnynanjiang.glamcorner.fixture.buildBoard
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class InputValidatorTest {
    lateinit var inputValidator: InputValidator

    @Rule
    @JvmField
    var expectedException = ExpectedException.none()

    @Before
    fun setUp() {
        inputValidator = InputValidator(buildBoard())
    }

    @Test
    fun shouldThrowExceptionForInvalidInput() {
        expectedException.expect(InvalidInputException::class.java)
        expectedException.expectMessage("Input should be a valid number between 0 and 6")
        inputValidator.validate("x")
    }

    @Test
    fun shouldThrowExceptionForOutOfUpperRangeInput() {
        expectedException.expect(InvalidInputException::class.java)
        expectedException.expectMessage("Column number out of range, should be between 0 and 6")
        inputValidator.validate("7")
    }

    @Test
    fun shouldThrowExceptionForOutOfLowerRangeInput() {
        expectedException.expect(InvalidInputException::class.java)
        expectedException.expectMessage("Column number out of range, should be between 0 and 6")
        inputValidator.validate("-1")
    }

    @Test
    fun shouldThrowExceptionIfColumnIsFull() {
        expectedException.expect(InvalidInputException::class.java)
        expectedException.expectMessage("Column 2 is full")
        inputValidator.validate("2")
    }
}
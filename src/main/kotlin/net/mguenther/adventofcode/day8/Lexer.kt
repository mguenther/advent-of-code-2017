package net.mguenther.adventofcode.day8


/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Lexer(val input: String) {

    private val WHITESPACE: Array<Char> = arrayOf(' ', '\t', '\n')
    private var index = 0
    private var currentChar: Char? = null

    init {
        if (input.trim().isEmpty()) {
            currentChar = null
        } else {
            consume()
        }
    }

    fun next(): Token {

        if (isEof()) {
            return Eof
        }

        while (isWhitespace()) consume()

        if (isEqualsSign()) {
            consume()
            if (isEqualsSign()) {
                consume()
                return Comparison("==")
            } else {
                throw LexerException("Expected a '==' but got only '='.")
            }
        }

        if (isLower()) {
            consume()
            if (isEqualsSign()) {
                consume()
                return Comparison("<=")
            } else {
                return Comparison("<")
            }
        }

        if (isGreater()) {
            consume()
            if (isEqualsSign()) {
                consume()
                return Comparison(">=")
            } else {
                return Comparison(">")
            }
        }

        if (isExclamationMark()) {
            consume()
            if (isEqualsSign()) {
                consume()
                return Comparison("!=")
            } else {
                throw LexerException("Expected a '!=' but only '!'.")
            }
        }

        if (isMinus()) {
            consume()
            return Minus
        }

        if (isNumeric()) {
            return Number(readNumber())
        }

        if (isAlpha()) {

            val characters = readCharacters()

            when (characters) {
                "inc" -> return Increment
                "dec" -> return Decrement
                "if" -> return If
                else -> return Register(characters)
            }
        }

        throw LexerException("Unable to break stream of characters into separate tokens because of an unrecognized character: " + currentChar)
    }

    private fun consume() {
        if (!isFinished()) {
            currentChar = input.get(index)
        } else {
            currentChar = null
        }
        index++
    }

    private fun readNumber(): String {
        val buffer = StringBuilder()
        buffer.append(currentChar)
        var detectedTokenBoundary = false
        while (!isFinished()) {
            consume()
            if (isWhitespace()) {
                detectedTokenBoundary = true
                break
            }
            if (!isNumeric()) {
                detectedTokenBoundary = true
                break
            }
            buffer.append(currentChar)
        }
        if (!detectedTokenBoundary) {
            consume() // consumes the last character that we have read, before breaking the while
        }
        return buffer.toString()
    }

    private fun readCharacters(): String {
        val buffer = StringBuilder()
        buffer.append(currentChar)
        var detectedTokenBoundary = false
        while (!isFinished()) {
            consume()
            if (isWhitespace()) {
                detectedTokenBoundary = true
                break
            }
            buffer.append(currentChar)
        }
        if (!detectedTokenBoundary) {
            consume() // consumes the last character that we have read, before breaking the while
        }
        return buffer.toString()
    }

    private fun isWhitespace(): Boolean = WHITESPACE.any { c -> c == currentChar }
    private fun isEqualsSign(): Boolean = matchesCharacter('=')
    private fun isLower(): Boolean = matchesCharacter('<')
    private fun isGreater(): Boolean = matchesCharacter('>')
    private fun isMinus(): Boolean = matchesCharacter('-')
    private fun isExclamationMark(): Boolean = matchesCharacter('!')
    private fun isNumeric(): Boolean = currentChar?.isDigit() ?: false
    private fun isAlpha(): Boolean = currentChar?.isLetter() ?: false
    private fun isEof(): Boolean = currentChar == null
    private fun isFinished(): Boolean = index >= input.length

    private fun matchesCharacter(expected: Char): Boolean = currentChar == expected
}

class LexerException(message: String) : RuntimeException(message)

sealed class Token(val type: String, val value: String = "")

object Increment : Token("INCREMENT", "inc")
object Decrement : Token("DECREMENT", "dec")
object Minus : Token("MINUS", "-")
object If : Token("IF", "if")
object Eof : Token("EOF")

data class Number(val number: String) : Token("NUMBER", number)
data class Register(val name: String) : Token("REGISTER", name)
data class Comparison(val operator: String) : Token("COMPARISON", operator)
package net.mguenther.adventofcode.day9

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Lexer(val input: String) {

    private val WHITESPACE: Array<Char> = arrayOf(' ', ',', '\t', '\n')
    private var index = 0
    private var currentChar: Char? = null
    private var levelOfGarbage: Int = 0
    private var omittedCharacters: Int = 0

    init {
        if (input.trim().isEmpty()) {
            currentChar = null
        } else {
            consume()
        }
    }

    private fun consume(): Int {
        var read = 0
        if (!isFinished()) {
            currentChar = input.get(index)
            read = 1

        } else {
            currentChar = null
        }
        index++
        return read
    }

    private fun isFinished(): Boolean = index >= input.length

    fun next(): Token {

        if (isEof()) {
            println("Classified " + omittedCharacters + " characters as garbage and omitted them from during lexing.")
            return Eof
        }

        while (skipCharacters()) {
            if (isExclamationMark()) {
                consume()
                consume()
            } else {
                val skipped = consume()
                if (levelOfGarbage > 0) omittedCharacters += skipped
            }
        }

        val token = when (currentChar) {
            '{' -> {
                consume()
                OpeningGroup
            }
            '}' -> {
                consume()
                ClosingGroup
            }
            '<' -> {
                levelOfGarbage++
                consume()
                OpeningGarbage
            }
            '>' -> {
                levelOfGarbage--
                consume()
                ClosingGarbage
            }
            else -> throw LexerException("Unable to recognize input character: " + currentChar)
        }

        return token
    }

    private fun isEof(): Boolean = currentChar == null

    private fun isWhitespace(): Boolean = when {
        levelOfGarbage > 0 -> currentChar != '>' && !isExclamationMark()
        else -> WHITESPACE.any { c -> c == currentChar }
    }

    private fun isExclamationMark(): Boolean = currentChar == '!'
    private fun skipCharacters(): Boolean = isWhitespace() || isExclamationMark()

    fun currentIndex(): Int = index
    fun omittedCharacters(): Int = omittedCharacters
}

class LexerException(message: String) : RuntimeException(message)

sealed class Token(val type: String, val value: String = "")

object OpeningGroup : Token("OPENING_GROUP", "{")
object ClosingGroup : Token("CLOSING_GROUP", "}")
object OpeningGarbage : Token("OPENING_GARBAGE", "<")
object ClosingGarbage : Token("CLOSING_GARBAGE", ">")
object Eof : Token("EOF")

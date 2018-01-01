package net.mguenther.adventofcode.day9

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Parser(val input: String) {

    private val lexer = Lexer(input)
    private var lookahead: Token? = null

    init {
        consume()
    }

    fun parse(): Group {

        if (isEof()) throw ParserException("Given input is empty.")

        val group = matchGroup()

        if (!isEof()) throw ParserException("Unable to parse the whole input.")

        return group
    }

    private fun matchGroup(): Group {
        ignoreGarbage()
        match("OPENING_GROUP")
        ignoreGarbage()
        val children = mutableListOf<Group>()
        while (isBeginningOfGroup()) {
            children.add(matchGroup())
        }
        ignoreGarbage()
        match("CLOSING_GROUP")
        return Group(children)
    }

    private fun ignoreGarbage() {
        while (isGarbage()) {
            match("OPENING_GARBAGE")
            match("CLOSING_GARBAGE")
        }
    }

    private fun match(tokenType: String): Token? {
        if (lookahead?.type.equals(tokenType)) {
            val matchedToken = lookahead
            consume()
            return matchedToken
        } else {
            throw ParserException("Expected token of type " + tokenType + " but got " + lookahead?.type + " [" + lexer.currentIndex() + "]")
        }
    }

    private fun consume() { lookahead = lexer.next() }
    private fun isEof(): Boolean = lookahead?.equals(Eof)!!
    private fun isBeginningOfGroup(): Boolean = lookahead?.equals(OpeningGroup)!!
    private fun isGarbage(): Boolean = lookahead?.equals(OpeningGarbage)!!

}

class ParserException(message: String) : RuntimeException(message)

data class Group(val children: List<Group> = emptyList())

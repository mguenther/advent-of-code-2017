package net.mguenther.adventofcode.day8

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Parser(program: String) {

    private val lexer = Lexer(program)
    private var lookahead: Token? = null

    init {
        consume()
    }

    fun parse(): List<Instruction> {

        if (isEof()) {
            throw ParserException("Given program contains no instructions.")
        }

        val listOfInstructions = match()

        if (hasTokens()) {
            throw ParserException("Unable to parse the whole program.")
        }

        return listOfInstructions
    }

    private fun match(): List<Instruction> {
        val listOfInstructions: MutableList<Instruction> = mutableListOf()
        while (hasTokens()) {
            listOfInstructions.add(matchInstruction())
        }
        return listOfInstructions
    }

    private fun matchInstruction(): Instruction {

        val onRegister = match("REGISTER")?.value!!

        if (isIncrement()) {
            match("INCREMENT")
            val factor = matchOptionalNegation()
            val by = factor * match("NUMBER")?.value!!.toInt()
            return IncrementStatement(onRegister, by, matchConditional())
        } else {
            match("DECREMENT")
            val factor = matchOptionalNegation()
            val by = factor * match("NUMBER")?.value!!.toInt()
            return DecrementStatement(onRegister, by, matchConditional())
        }
    }

    private fun matchOptionalNegation(): Int {
        if (isMinus()) {
            match("MINUS")
            return -1
        } else {
            return 1
        }
    }

    private fun matchConditional(): ConditionalStatement {
        match("IF")
        val onRegister = match("REGISTER")?.value!!
        val operator = match("COMPARISON")?.value!!
        val factor = matchOptionalNegation()
        val expectedValue = factor * match("NUMBER")?.value!!.toInt()
        return ConditionalStatement(onRegister, operator, expectedValue)
    }

    private fun match(expectedToken: String): Token? {
        if (lookahead?.type.equals(expectedToken)) {
            val matchedToken = lookahead
            consume()
            return matchedToken
        } else {
            throw ParserException("Expected token of type " + expectedToken + " but got " + lookahead)
        }
    }

    private fun isIncrement(): Boolean = lookahead?.type.equals("INCREMENT")
    private fun isMinus(): Boolean = lookahead?.type.equals("MINUS")
    private fun isEof(): Boolean = lookahead?.equals(Eof)!!
    private fun hasTokens(): Boolean = !isEof()
    private fun consume() {
        lookahead = lexer.next()
    }
}

class ParserException(message: String) : RuntimeException(message)

sealed class Instruction

data class IncrementStatement(val register: String, val by: Int, val onCondition: ConditionalStatement) : Instruction()
data class DecrementStatement(val register: String, val by: Int, val onCondition: ConditionalStatement) : Instruction()
data class ConditionalStatement(val register: String, val operator: String, val expectedValue: Int) : Instruction()
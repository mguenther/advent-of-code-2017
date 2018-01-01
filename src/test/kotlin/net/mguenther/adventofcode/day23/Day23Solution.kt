package net.mguenther.adventofcode.day23

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day23Solution {

    @Test
    fun should_show_solution_to_part_one() {
        val coprocessor = CoProcessor()
        coprocessor.process(loadInstructions())
        println(coprocessor.invocationsOfMul())
        println(coprocessor.deref("h"))
    }

    @Test
    fun should_show_solution_to_part_two() {
        println(refactoredAsm())
    }

    private fun loadInstructions(): List<Instruction> {

        val instructions =  Day23Solution::class.java
                .getResource("/day_23.input")
                .readText()
                .split("\n")
                .map { line -> parseLine(line) }

        instructions.forEach { i -> println(i) }

        return instructions
    }

    private fun parseLine(line: String): Instruction = when (line.split(" ")[0]) {
        "set" -> parseSet(line)
        "sub" -> parseSub(line)
        "mul" -> parseMul(line)
        "jnz" -> parseJnz(line)
        else -> throw IllegalArgumentException("unrecognizable input")
    }

    private fun parseSet(set: String): Instruction {
        val tokens = set.split(" ")
        val targetRegister = tokens[1]
        if (isDigit(tokens[2])) {
            val to = tokens[2].toInt()
            return SetByVal(targetRegister, to)
        } else {
            val sourceRegister = tokens[2]
            return SetByRef(targetRegister, sourceRegister)
        }
    }

    private fun parseSub(sub: String): Instruction {
        val tokens = sub.split(" ")
        val targetRegister = tokens[1]
        if (isDigit(tokens[2])) {
            val by = tokens[2].toInt()
            return SubByVal(targetRegister, by)
        } else {
            val sourceRegister = tokens[2]
            return SubByRef(targetRegister, sourceRegister)
        }
    }

    private fun parseMul(mul: String): Instruction {
        val tokens = mul.split(" ")
        val targetRegister = tokens[1]
        if (isDigit(tokens[2])) {
            val by = tokens[2].toInt()
            return MulByVal(targetRegister, by)
        } else {
            val sourceRegister = tokens[2]
            return MulByRef(targetRegister, sourceRegister)
        }
    }

    private fun parseJnz(jnz: String): Instruction {
        val tokens = jnz.split(" ")
        val offset = tokens[2].toInt()
        if (isDigit(tokens[1])) {
            val compareAgainst = tokens[1].toInt()
            return JnzByVal(compareAgainst, offset)
        } else {
            val register = tokens[1]
            return JnzByRef(register, offset)
        }
    }

    private fun isDigit(token: String): Boolean = token.toCharArray()[0].isDigit() || token.toCharArray()[0] == '-'
}
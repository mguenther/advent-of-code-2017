package net.mguenther.adventofcode.day18

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun instructionsFromResource(resourceFile: String): List<Instruction> {
    return Thread::class.java
            .getResource(resourceFile)
            .readText()
            .split("\n")
            .map { toInstruction(it) }
}

private fun toInstruction(line: String): Instruction {
    val tokens = line.split(" ")
    if (tokens.size == 2) {
        return Instruction(tokens[0], tokens[1])
    } else {
        return Instruction(tokens[0], tokens[1], tokens[2])
    }
}

typealias Register = String

data class Instruction(val command: String, val target: Register, val param: String = "")

package net.mguenther.adventofcode.day9

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Interpreter {

    fun run(input: String): Int {
        return evaluate(Parser(input).parse())
    }

    private fun evaluate(group: Group): Int = evaluate(group, 1)

    private fun evaluate(group: Group, level: Int): Int {
        return level + group.children.map { child -> evaluate(child, level + 1) }.sum()
    }
}
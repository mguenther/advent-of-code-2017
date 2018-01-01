package net.mguenther.adventofcode.day8

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day8Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(Interpreter().run(loadProgram()).first.values.max())
    }

    @Test
    fun should_print_solution_to_part_two() {
        println(Interpreter().run(loadProgram()).second)
    }

    private fun loadProgram(): String = Day8Solution::class.java.getResource("/day_8.input").readText()
}
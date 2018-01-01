package net.mguenther.adventofcode.day9

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day9Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(Interpreter().run(loadInput()))
    }

    private fun loadInput(): String = Day9Solution::class.java.getResource("/day_9.input").readText()
}
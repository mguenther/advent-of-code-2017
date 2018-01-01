package net.mguenther.adventofcode.day11

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Day11Solution {

    @Test
    fun should_print_solution_to_par_one_and_two() {
        val moves = Day11Solution::class.java
                .getResource("/day_11.input")
                .readText()
                .split(",")
        hexDistance(moves)
    }
}
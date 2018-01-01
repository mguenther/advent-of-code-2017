package net.mguenther.adventofcode.day21

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day21Solution {

    @Test
    fun should_show_solution_to_part_one() {
        solve(readRules("/day_21.input"), iterations = 4)
    }

    @Test
    fun should_show_solution_to_part_two() {
        solve(readRules("/day_21.input"), iterations = 17)
    }
}
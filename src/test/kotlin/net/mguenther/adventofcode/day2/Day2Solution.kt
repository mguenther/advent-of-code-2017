package net.mguenther.adventofcode.day2

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day2Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(Day2Solution::class.java
                .getResource("/day_2.input")
                .readText()
                .split("\n")
                .map { row -> minmax(row.split(" ")) }
                .map { minmax -> diff(minmax) }
                .reduce { t: Int, u: Int -> t + u })
    }

    @Test
    fun should_print_solution_to_part_two() {
        println(Day2Solution::class.java
                .getResource("/day_2.input")
                .readText()
                .split("\n")
                .map { row -> evenlyDivides(row.split(" ")) }
                .reduce { t: Int, u: Int -> t + u })
    }
}
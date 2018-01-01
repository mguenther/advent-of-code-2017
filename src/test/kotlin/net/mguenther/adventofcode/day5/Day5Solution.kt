package net.mguenther.adventofcode.day5

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day5Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(maze(loadOffsets()))
    }

    @Test
    fun should_print_solution_to_part_two() {
        println(strangerMaze(loadOffsets()))
    }

    fun loadOffsets(): IntArray {
        return Day5Solution::class.java
                .getResource("/day_5.input")
                .readText()
                .split(",")
                .map { value -> value.toInt() }
                .toIntArray()
    }
}
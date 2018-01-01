package net.mguenther.adventofcode.day19

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day19Solution {

    @Test
    fun should_print_solution_for_part_one_and_part_two() {
        val grid = load()
        val tubular = Tubular(grid)
        val startPos = Position(grid[0].indexOf('|'), 0)
        val startDir = Direction(0, 1)
        println("starting position $startPos, direction $startDir")
        println(tubular.walk(startPos, startDir))
    }

    private fun load(): List<CharArray> = Day19Solution::class.java
            .getResource("/day_19.input")
            .readText()
            .split("\n")
            .map { line -> line.toCharArray() }
}
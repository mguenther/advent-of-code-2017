package net.mguenther.adventofcode.day3

import net.mguenther.adventofcode.day3.SquareFiller
import net.mguenther.adventofcode.day3.distance
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day3Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(distance(265149))
    }

    @Test
    fun should_print_solution_to_part_two() {
        SquareFiller(265149).fill()
    }
}
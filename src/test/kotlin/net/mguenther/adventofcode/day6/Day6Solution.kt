package net.mguenther.adventofcode.day6

import net.mguenther.adventofcode.day6.redistributions
import net.mguenther.adventofcode.day6.redistributionsInLoop
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day6Solution {

    @Test
    fun should_print_solution_to_part_one() {
        val memoryBanks = intArrayOf(5, 1, 10, 0, 1, 7, 13, 14, 3, 12, 8, 10, 7, 12, 0, 6)
        println(redistributions(memoryBanks)) // 5042
    }

    @Test
    fun should_print_solution_to_part_two() {
        val memoryBanks = intArrayOf(5, 1, 10, 0, 1, 7, 13, 14, 3, 12, 8, 10, 7, 12, 0, 6)
        println(redistributionsInLoop(memoryBanks)) // 1086
    }
}
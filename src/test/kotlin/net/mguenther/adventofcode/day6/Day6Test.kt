package net.mguenther.adventofcode.day6

import net.mguenther.adventofcode.day6.redistribute
import net.mguenther.adventofcode.day6.redistributions
import net.mguenther.adventofcode.day6.redistributionsInLoop
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day6Test {

    @Test
    fun redistribute_one_time_should_result_in_correct_block_distribution() {
        val memoryBanks = intArrayOf(0, 2, 7, 0)
        redistribute(memoryBanks)
        assertThat(memoryBanks[0], `is`(2))
        assertThat(memoryBanks[1], `is`(4))
        assertThat(memoryBanks[2], `is`(1))
        assertThat(memoryBanks[3], `is`(2))
    }

    @Test
    fun redistribute_twice_should_result_in_correct_block_distribution() {
        val memoryBanks = intArrayOf(0, 2, 7, 0)
        redistribute(memoryBanks)
        redistribute(memoryBanks)
        assertThat(memoryBanks[0], `is`(3))
        assertThat(memoryBanks[1], `is`(1))
        assertThat(memoryBanks[2], `is`(2))
        assertThat(memoryBanks[3], `is`(3))
    }

    @Test
    fun redistributions_should_yield_number_of_redistribution_cycles_until_a_distribution_is_seen_twice() {
        val memoryBanks = intArrayOf(0, 2, 7, 0)
        assertThat(redistributions(memoryBanks), `is`(5))
    }

    @Test
    fun redistributionsInLoop_should_yield_solution_from_minimal_example() {
        val memoryBanks = intArrayOf(0, 2, 7, 0)
        assertThat(redistributionsInLoop(memoryBanks), `is`(4))
    }
}
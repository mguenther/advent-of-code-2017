package net.mguenther.adventofcode.day12

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day12Test {

    @Test
    fun should_yield_solution_for_example_for_part_one() {
        var nodes = mutableMapOf(
                Pair(0, listOf(2)),
                Pair(1, listOf(1)),
                Pair(2, listOf(0, 3, 4)),
                Pair(3, listOf(2, 4)),
                Pair(4, listOf(2, 3, 6)),
                Pair(5, listOf(6)),
                Pair(6, listOf(4,5)))
        assertThat(programsInRootComponent(nodes), `is`(6))
    }
}

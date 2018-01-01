package net.mguenther.adventofcode.day24

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day24Test {

    @Test
    fun is_compatible_should_yield_true_if_at_least_one_port_matches() {
        val left = Component(0, 1)
        val right = Component(10, 1)

        assertTrue(left.isCompatible(right))
    }

    @Test
    fun is_compatible_should_yield_false_if_no_port_matches() {
        val left = Component(3, 7)
        val right = Component(4, 8)

        assertFalse(left.isCompatible(right))
    }

    @Test
    fun strength_should_be_the_sum_of_ports() {
        assertThat(Component(3, 7).strength(), `is`(10))
    }

    @Test
    @Ignore
    fun should_yield_solution_for_example_for_part_one() {

        val components = listOf(
                Component(0, 2),
                Component(2, 2),
                Component(2, 3),
                Component(3, 4),
                Component(3, 5),
                Component(0, 1),
                Component(10, 1),
                Component(9, 10))

        val host = ElectromagneticHost()
        assertThat(host.solve(components), `is`(31))
    }
}

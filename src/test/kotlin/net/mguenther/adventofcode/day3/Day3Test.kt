package net.mguenther.adventofcode.day3

import net.mguenther.adventofcode.day3.corner
import net.mguenther.adventofcode.day3.distance
import net.mguenther.adventofcode.day3.layer
import net.mguenther.adventofcode.day3.start
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day3Test {

    @Test
    fun should_yield_zero_if_data_is_from_center() {
        assertThat(distance(1), `is`(0))
    }

    @Test
    fun should_yield_correct_steps_for_horizontal_and_vertical_displacement_from_center() {
        assertThat(distance(12), `is`(3))
    }

    @Test
    fun should_yield_correct_steps_for_vertical_displacement_from_center() {
        assertThat(distance(23), `is`(2))
    }

    @Test
    fun should_yield_correct_steps_for_larger_numbers() {
        assertThat(distance(1024), `is`(31))
    }

    @Test
    fun show_results_for_layer_two() {
        val expected: List<Pair<Int, Int>> = listOf(
                Pair(10, 3), Pair(11, 2), Pair(12, 3), Pair(13, 4),
                Pair(14, 3), Pair(15, 2), Pair(16, 3), Pair(17, 4),
                Pair(18, 3), Pair(19, 2), Pair(20, 3), Pair(21, 4),
                Pair(22, 3), Pair(23, 2), Pair(24, 3), Pair(25, 4))
        for (pair in expected)
            println(
                    "distance(" + pair.first + ") = " + distance(pair.first) + " " +
                    "(expected: " + pair.second + "), corner(" + pair.first + ") = " + corner(pair.first) + ", " +
                    "start(" + pair.first + ") = " + start(pair.first) + ", " +
                    "layer(" + pair.first + ") = " + layer(pair.first))
    }
}

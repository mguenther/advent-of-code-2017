package net.mguenther.adventofcode.day2

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day2Test {

    @Test
    fun should_calculate_correct_checksum_on_minmax_sum_per_row() {
        val checksum = listOf<String>("5 1 9 5", "7 5 3", "2 4 6 8")
                .map { row -> minmax(row.split(" ")) }
                .map { minmax -> diff(minmax) }
                .reduce { t: Int, u: Int -> t + u }
        assertThat(checksum, `is`(18))
    }

    @Test
    fun should_calculate_correct_checksum_on_evenly_division_per_row() {
        val checksum = listOf<String>("5 9 2 8", "9 4 7 3", "3 8 6 5")
                .map { row -> evenlyDivides(row.split(" ")) }
                .reduce { t: Int, u: Int -> t + u }
        assertThat(checksum, `is`(9))
    }
}

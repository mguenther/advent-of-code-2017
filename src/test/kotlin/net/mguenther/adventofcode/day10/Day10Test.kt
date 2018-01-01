package net.mguenther.adventofcode.day10

import net.mguenther.adventofcode.day10.KnotHash
import net.mguenther.adventofcode.day10.KnotHashFirst
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day10Test {

    @Test
    fun should_yield_correct_result_for_example() {
        val hash = KnotHashFirst(5)
        assertThat(hash.knot(intArrayOf(3, 4, 1, 5)), `is`(12))
    }

    @Test
    fun should_yield_correct_result_after_restarting_at_offset_0_in_the_middle() {
        val hash = KnotHashFirst(5)
        assertThat(hash.knot(intArrayOf(5, 2, 4)), `is`(0))
    }

    @Test
    fun should_encode_empty_string_to_correct_hash() {
        assertThat(KnotHash(256).checksum(""), `is`("a2582a3a0e66e6e86e3812dcb672a272"))
        assertThat(KnotHash(256).checksum("AoC 2017"), `is`("33efeb34ea91902bb2f59c9920caa6cd"))
        assertThat(KnotHash(256).checksum("1,2,3"), `is`("3efbe78a8d82f29979031a4aa0b16a9d"))
        assertThat(KnotHash(256).checksum("1,2,4"), `is`("63960835bcdc130f0b66d7ff4f6a5a8e"))
    }
}
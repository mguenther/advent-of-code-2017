package net.mguenther.adventofcode.day1

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day1Test {

    @Test
    fun using_successor_should_yield_sum_of_individual_matches() {
        assertThat(captcha(arrayListOf<Int>(1, 1, 2, 2), ::successor), `is`(3))
    }

    @Test
    fun using_successor_should_yield_correct_result_if_all_digits_match() {
        assertThat(captcha(arrayListOf<Int>(1, 1, 1, 1), ::successor), `is`(4))
    }

    @Test
    fun using_successor_should_yield_zero_if_nothing_matches() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 3, 4), ::successor), `is`(0))
    }

    @Test
    fun using_successor_should_match_on_last_digit_and_wrap_around() {
        assertThat(captcha(arrayListOf<Int>(9, 1, 2, 1, 2, 1, 2, 9), ::successor), `is`(9))
    }

    @Test
    fun using_halfway_should_yield_sum_of_individual_matches() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 1, 2), ::halfway), `is`(6))
    }

    @Test
    fun using_halfway_should_yield_zero_if_nothing_matches() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 2, 1), ::halfway), `is`(0))
    }

    @Test
    fun using_halfway_should_yield_correct_result_on_single_match() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 3, 4, 2, 5), ::halfway), `is`(4))
    }

    @Test
    fun using_halfway_should_yield_correct_result_if_everything_matches() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 3, 1, 2, 3), ::halfway), `is`(12))
    }

    @Test
    fun using_halfway_should_yield_correct_result_if_same_digit_matches_multiple_times() {
        assertThat(captcha(arrayListOf<Int>(1, 2, 1, 3, 1, 4, 1, 5), ::halfway), `is`(4))
    }
}

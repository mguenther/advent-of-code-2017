package net.mguenther.adventofcode.day21

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day21Test {

    @Test
    fun split_should_return_list_patterns_containing_same_pattern_if_size_is_2() {
        val listOfPatterns = Pattern("12/34").split()
        assertThat(listOfPatterns.size, `is`(1))
        assertThat(listOfPatterns.get(0), `is`(Pattern("12/34")))
    }

    @Test
    fun split_should_return_list_of_patterns_containing_same_pattern_if_size_is_3() {
        val listOfPatterns = Pattern("123/456").split()
        assertThat(listOfPatterns.size, `is`(1))
        assertThat(listOfPatterns.get(0), `is`(Pattern("123/456")))
    }

    @Test
    fun split_should_return_list_of_patterns_split_with_segment_size_of_2_in_even_case() {
        val listOfPatterns = Pattern("1234/5678/90AB/CDEF").split()
        assertThat(listOfPatterns.size, `is`(4))
        assertTrue(listOfPatterns.contains(Pattern("12/56")))
        assertTrue(listOfPatterns.contains(Pattern("34/78")))
        assertTrue(listOfPatterns.contains(Pattern("90/CD")))
        assertTrue(listOfPatterns.contains(Pattern("AB/EF")))
    }
}
package net.mguenther.adventofcode.day18

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class PartOneTest {

    @Test
    fun should_yield_correct_result_for_example_to_part_one() {
        val instructions = instructionsFromResource("/day_18_test.input")
        val duet = PartOne()
        assertThat(duet.execute(instructions), `is`(4L))
    }
}
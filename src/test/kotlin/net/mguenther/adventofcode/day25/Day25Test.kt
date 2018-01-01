package net.mguenther.adventofcode.day25

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day25Test {

    @Test
    fun should_yield_correct_result_for_example_for_part_one() {
        val tm = TuringMachine()

        tm.addState("A", { value -> when (value) {
            0    -> Instruction(1, Right, "B")
            else -> Instruction(0, Left, "B")
        }})

        tm.addState("B", { value -> when (value) {
            0    -> Instruction(1, Left, "A")
            else -> Instruction(1, Right, "A")
        }})

        tm.tick(6)
        assertThat(tm.checksum(), `is`(3))
    }
}
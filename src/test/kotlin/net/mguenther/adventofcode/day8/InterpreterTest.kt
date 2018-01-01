package net.mguenther.adventofcode.day8

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class InterpreterTest {

    @Test
    fun should_evaluate_correct_solution_for_minimal_example() {

        val program =
                "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10"
        val pair = Interpreter().run(program)
        assertThat(pair.first.values.max(), `is`(1))
        assertThat(pair.second, `is`(10))
    }
}
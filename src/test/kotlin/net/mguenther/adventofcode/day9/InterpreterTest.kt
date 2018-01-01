package net.mguenther.adventofcode.day9

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class InterpreterTest {

    @Test
    fun should_yield_correct_results_for_examples_to_first_problem() {
        val interpreter = Interpreter()
        assertThat(interpreter.run("{}"), `is`(1))
        assertThat(interpreter.run("{{{}}}"), `is`(6))
        assertThat(interpreter.run("{{},{}}"), `is`(5))
        assertThat(interpreter.run("{{{},{},{{}}}}"), `is`(16))
        assertThat(interpreter.run("{<a>,<a>,<a>,<a>}"), `is`(1))
        assertThat(interpreter.run("{{<ab>},{<ab>},{<ab>},{<ab>}}"), `is`(9))
        assertThat(interpreter.run("{{<!!>},{<!!>},{<!!>},{<!!>}}"), `is`(9))
        assertThat(interpreter.run("{{<a!>},{<a!>},{<a!>},{<ab>}}"), `is`(3))
    }
}
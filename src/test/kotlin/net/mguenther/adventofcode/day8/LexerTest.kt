package net.mguenther.adventofcode.day8

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class LexerTest {

    @Test
    fun should_recognize_increment_statement_with_greater_than_conditional() {
        val lexer = Lexer("b inc 5 if a > 1")
        assertThat(lexer.next().value, `is`(Register("b").value))
        assertThat(lexer.next().value, `is`(Increment.value))
        assertThat(lexer.next().value, `is`(Number("5").value))
        assertThat(lexer.next().value, `is`(If.value))
        assertThat(lexer.next().value, `is`(Register("a").value))
        assertThat(lexer.next().value, `is`(Comparison(">").value))
        assertThat(lexer.next().value, `is`(Number("1").value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_increment_statement_with_lower_than_conditional() {
        val lexer = Lexer("a inc 1 if b < 5")
        assertThat(lexer.next().value, `is`(Register("a").value))
        assertThat(lexer.next().value, `is`(Increment.value))
        assertThat(lexer.next().value, `is`(Number("1").value))
        assertThat(lexer.next().value, `is`(If.value))
        assertThat(lexer.next().value, `is`(Register("b").value))
        assertThat(lexer.next().value, `is`(Comparison("<").value))
        assertThat(lexer.next().value, `is`(Number("5").value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_decrement_statement_with_negative_number_and_greater_or_equals_conditional() {
        val lexer = Lexer("c dec -10 if a >= 1")
        assertThat(lexer.next().value, `is`(Register("c").value))
        assertThat(lexer.next().value, `is`(Decrement.value))
        assertThat(lexer.next().value, `is`(Minus.value))
        assertThat(lexer.next().value, `is`(Number("10").value))
        assertThat(lexer.next().value, `is`(If.value))
        assertThat(lexer.next().value, `is`(Register("a").value))
        assertThat(lexer.next().value, `is`(Comparison(">=").value))
        assertThat(lexer.next().value, `is`(Number("1").value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_increment_statement_with_negative_number_and_equals_conditional() {
        val lexer = Lexer("c inc -20 if c == 10")
        assertThat(lexer.next().value, `is`(Register("c").value))
        assertThat(lexer.next().value, `is`(Increment.value))
        assertThat(lexer.next().value, `is`(Minus.value))
        assertThat(lexer.next().value, `is`(Number("20").value))
        assertThat(lexer.next().value, `is`(If.value))
        assertThat(lexer.next().value, `is`(Register("c").value))
        assertThat(lexer.next().value, `is`(Comparison("==").value))
        assertThat(lexer.next().value, `is`(Number("10").value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }
}

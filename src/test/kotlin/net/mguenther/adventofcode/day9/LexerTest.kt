package net.mguenther.adventofcode.day9

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class LexerTest {

    @Test
    fun should_recognize_empty_garbage() {
        val lexer = Lexer("<>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_dismiss_random_characters_inside_garbage() {
        val lexer = Lexer("<random characters>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_dismiss_opening_garbage_tokens_inside_garbage() {
        val lexer = Lexer("<<<<>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_ignore_closing_garbage_token_inside_garbage() {
        val lexer = Lexer("<{!>}>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun exclamation_marks_should_cancel_each_other_out() {
        val lexer = Lexer("<!!>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun subsequent_exclamation_marks_should_cancel_out_correctly() {
        val lexer = Lexer("<!!!>>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun garbage_should_end_at_first_closing_garbage_token() {
        val lexer = Lexer("<{o\"i!a,<{i<a>")
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_single_group() {
        val lexer = Lexer("{}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_nested_groups() {
        val lexer = Lexer("{{{}}}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_subsequent_nested_groups_delimited_by_whitespace() {
        val lexer = Lexer("{{},{}}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_nested_groups_with_subsequent_groups() {
        val lexer = Lexer("{{{},{},{{}}}}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_garbage_inside_a_group() {
        val lexer = Lexer("{<{},{},{{}}>}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_multiple_garbage_containers_inside_a_group() {
        val lexer = Lexer("{<a>,<a>,<a>,<a>}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_garbage_within_nested_groups() {
        val lexer = Lexer("{{<a>},{<a>},{<a>},{<a>}}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))

        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))

        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))

        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))

        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))

        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_recognize_ignores_within_garbage_in_nested_groups() {
        val lexer = Lexer("{{<!>},{<!>},{<!>},{<a>}}")
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGroup.value))
        assertThat(lexer.next().value, `is`(OpeningGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGarbage.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(ClosingGroup.value))
        assertThat(lexer.next().value, `is`(Eof.value))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_for_empty_garbage_container() {
        val lexer = Lexer("<>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(0))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_for_garbage_container_with_random_letters() {
        val lexer = Lexer("<random characters>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(17))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_for_garbage_brackets_inside_garbage_container() {
        val lexer = Lexer("<<<<>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(3))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_for_groups_with_skips_inside_garbage_container() {
        val lexer = Lexer("<{!>}>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(2))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_with_cancelling_out_skips_inside_garbage_container() {
        val lexer = Lexer("<!!>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(0))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_with_subsequent_skips_inside_garbage_container() {
        val lexer = Lexer("<!!!>>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(0))
    }

    @Test
    fun should_yield_correct_number_of_omitted_characters_for_wild_mix_of_things_inside_garbage_container() {
        val lexer = Lexer("<{o\"i!a,<{i<a>")
        while (lexer.next() != Eof) {}
        assertThat(lexer.omittedCharacters(), `is`(10))
    }
}
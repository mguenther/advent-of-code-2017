package net.mguenther.adventofcode.day9

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class ParserTest {

    @Test
    fun should_parse_single_group() {
        val group = Parser("{}").parse()
        assertThat(group.children.isEmpty(), `is`(true))
    }

    @Test
    fun should_parse_multiple_nested_groups() {
        val group = Parser("{{},{}}").parse()
        assertThat(group.children.size, `is`(2))
    }

    @Test
    fun should_ignore_garbage_inside_group() {
        val group = Parser("{<{}>}").parse()
        assertThat(group.children.isEmpty(), `is`(true))
    }
}
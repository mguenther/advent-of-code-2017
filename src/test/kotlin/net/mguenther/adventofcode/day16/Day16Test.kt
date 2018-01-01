package net.mguenther.adventofcode.day16

import net.mguenther.adventofcode.day16.Exchange
import net.mguenther.adventofcode.day16.Partner
import net.mguenther.adventofcode.day16.Promenade
import net.mguenther.adventofcode.day16.Spin
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day16Test {

    @Test
    fun single_spin_should_move_programs_on_to_the_right() {
        val promenade = Promenade(5)
        promenade.dance(listOf(Spin(1)))
        assertThat(promenade.toString(), `is`("eabcd"))
    }

    @Test
    fun exchange_should_swap_programs_at_given_indices() {
        val promenade = Promenade(5)
        promenade.dance(listOf(Exchange(0, 1)))
        assertThat(promenade.toString(), `is`("bacde"))
    }

    @Test
    fun partner_should_swap_given_programs() {
        val promenade = Promenade(5)
        promenade.dance(listOf(Partner("e", "b")))
        assertThat(promenade.toString(), `is`("aecdb"))
    }

    @Test
    fun repeated_dance_should_result_in_correct_solution() {
        val promenade = Promenade(5)
        val moves = listOf(Spin(1), Exchange(3, 4), Partner("e", "b"))
        promenade.dance(moves, 2)
        assertThat(promenade.toString(), `is`("ceadb"))
    }
}
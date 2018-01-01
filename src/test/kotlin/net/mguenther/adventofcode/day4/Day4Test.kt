package net.mguenther.adventofcode.day4

import net.mguenther.adventofcode.day4.isPassphrase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day4Test {

    @Test
    fun should_yield_true_if_every_word_appears_only_once() {
        assertTrue(isPassphrase("aa bb cc dd ee"))
    }

    @Test
    fun should_yield_false_if_a_word_appears_multiple_times() {
        assertFalse(isPassphrase("aa bb cc dd aa"))
    }
}
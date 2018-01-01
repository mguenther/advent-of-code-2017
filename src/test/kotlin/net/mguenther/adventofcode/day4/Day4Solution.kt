package net.mguenther.adventofcode.day4

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day4Solution {

    @Test
    fun should_print_solution_to_part_one() {
        val numberOfPassphrases = Day4Solution::class.java
                .getResource("/day_4.input")
                .readText()
                .split("\n")
                .filter { phrase -> isPassphrase(phrase) }
                .count()
        println(numberOfPassphrases)
    }

    @Test
    fun should_print_solution_to_part_two() {
        val numberOfPassphrases = Day4Solution::class.java
                .getResource("/day_4.input")
                .readText()
                .split("\n")
                .filter { phrase -> isPassphrase(phrase, ::primeProductForWord) }
                .count()
        println(numberOfPassphrases)
    }
}
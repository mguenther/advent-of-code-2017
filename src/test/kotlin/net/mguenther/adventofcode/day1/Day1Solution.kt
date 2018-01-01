package net.mguenther.adventofcode.day1

import org.junit.Before
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day1Solution {

    private var listOfNumbers: List<Int> = listOf()

    @Before
    fun readDataFromResource() {
        listOfNumbers = Day1Solution::class.java
                .getResource("/day_1.input")
                .readText()
                .split("")
                .filter { n -> !n.isEmpty() }
                .map { n -> n.toInt() }
                .toCollection(arrayListOf<Int>())
    }

    @Test
    fun should_print_solution_to_part_one() {
        println(captcha(listOfNumbers, ::successor))
    }

    @Test
    fun should_print_solution_to_part_two() {
        println(captcha(listOfNumbers, ::halfway))
    }
}
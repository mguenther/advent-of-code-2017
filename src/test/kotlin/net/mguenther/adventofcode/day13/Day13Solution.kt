package net.mguenther.adventofcode.day13

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day13Solution {

    @Test
    fun should_show_solution_to_part_one() {
        println(severityOfTrip(loadFirewall("/day_13.input")))
    }

    @Test
    fun should_show_solution_to_part_two() {
        val layers = loadFirewall("/day_13.input")
        var delay = 0
        while (true) {
            if (layers.none{ it.caught(delay) }) {
                println(delay)
                break
            }
            delay++
        }
    }

    private fun loadFirewall(resourceFile: String): List<Layer> {
        return Day13Test::class.java.getResource(resourceFile)
                .readText()
                .split("\n")
                .map { line -> line.split(":").let { tokens -> Layer(tokens[0].trim().toInt(), tokens[1].trim().toInt())} }
    }
}
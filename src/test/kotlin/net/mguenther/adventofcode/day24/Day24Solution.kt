package net.mguenther.adventofcode.day24

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day24Solution {

    @Test
    fun should_print_solution_for_part_one() {
        val host = ElectromagneticHost()
        println(host.solve(loadComponents()))
    }

    private fun loadComponents(): List<Component> {

        return Day24Solution::class.java
                .getResource("/day_24.input")
                .readText()
                .split("\n")
                .map { line -> toComponent(line) }
    }

    private fun toComponent(line: String): Component {
        val ports = line.trim().split("/")
        return Component(ports[0].toInt(), ports[1].toInt())
    }
}

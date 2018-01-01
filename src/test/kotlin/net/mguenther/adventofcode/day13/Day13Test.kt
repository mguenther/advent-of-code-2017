package net.mguenther.adventofcode.day13

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day13Test {

    @Test
    fun test() {
        assertThat(severityOfTrip(loadFirewall("/day_13_test.input")), `is`(24))
    }

    private fun loadFirewall(resourceFile: String): List<Layer> {
        return Day13Test::class.java.getResource(resourceFile)
                .readText()
                .split("\n")
                .map { line -> line.split(":").let { tokens -> Layer(tokens[0].trim().toInt(), tokens[1].trim().toInt())} }
    }
}
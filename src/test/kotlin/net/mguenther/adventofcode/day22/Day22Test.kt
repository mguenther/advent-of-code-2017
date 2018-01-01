package net.mguenther.adventofcode.day22

import net.mguenther.adventofcode.array2dOfChar
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day22Test {

    val cluster =
            "..#\n" +
            "#..\n" +
            "..."

    @Test
    fun show_first_bursts_with_example_map() {

        val sporifica = Sporifica(readCluster(cluster))

        println(sporifica)

        sporifica.burst()

        println(sporifica)

        sporifica.burst()

        println(sporifica)
    }

    @Test
    fun show_first_100_bursts_with_example_map() {

        val sporifica = Sporifica(readCluster(cluster))
        sporifica.burst(100)
        println(sporifica)
        assertThat(sporifica.infections(), `is`(26))
    }

    @Test
    fun show_10000000_bursts_with_example_map() {

        val sporifica = Sporifica(readCluster(cluster))
        sporifica.burst(10000000)
        println(sporifica)
        assertThat(sporifica.infections(), `is`(2511944))
    }

    @Test
    fun show_solution_to_part_two() {

        val cluster = Day22Test::class.java.getResource("/day_22.input").readText()
        val sporifica = Sporifica(readCluster(cluster))
        sporifica.burst(10000000)
        println(sporifica.infections())
    }

    fun readCluster(cluster: String): Array<CharArray> {

        val rows = cluster.split("\n")
        val array = array2dOfChar(padding(rows.size), padding(rows.size))

        for (y in 0 until array.size) {
            for (x in 0 until array[y].size) {
                array[y][x] = '.'
            }
        }

        for (y in 0 until rows.size) {
            val cols = rows[y].split("")
            for (x in 1 until cols.size-1) {
                array[offset(rows.size) + y][offset(rows.size) + x - 1] = cols[x].toCharArray()[0]
            }
        }

        return array
    }

    private fun padding(size: Int) = 75 * size + size + 75 * size
    private fun offset(size: Int) = 75 * size
}
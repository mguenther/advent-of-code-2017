package net.mguenther.adventofcode.day10

import net.mguenther.adventofcode.day10.KnotHash
import net.mguenther.adventofcode.day10.KnotHashFirst
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day10Solution {

    @Test
    fun should_show_solution_to_part_one() {
        val hash = KnotHashFirst(256)
        println(hash.knot(intArrayOf(88, 88, 211, 106, 141, 1, 78, 254, 2, 111, 77, 255, 90, 0, 54, 205)))
    }

    @Test
    fun should_show_solution_to_part_two() {
        val hash = KnotHash(256)
        println(hash.checksum("88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205"))
    }
}
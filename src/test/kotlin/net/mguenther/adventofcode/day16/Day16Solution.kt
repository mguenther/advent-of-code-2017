package net.mguenther.adventofcode.day16

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day16Solution {

    @Test
    fun should_print_solution_to_part_one() {
        val promenade = Promenade(16)
        promenade.dance(loadMoves())
        println(promenade)
    }

    @Test
    fun should_print_solution_to_part_two() {
        val promenade = Promenade(16)
        // the end result is recurring, so it suffices to go with 10 repetitions instead of a billion
        promenade.dance(loadMoves(), 10)
        println(promenade)
    }

    fun loadMoves(): List<Move> {
        return Day16Solution::class.java
                .getResource("/day_16.input")
                .readText()
                .split(",")
                .map { toCommand(it) }
    }

    fun toCommand(value: String): Move {
        if (value.startsWith("s")) {
            return Spin(value.substring(1).toInt())
        } else if (value.startsWith("x")) {
            val segments = value.substring(1).split("/")
            return Exchange(segments.get(0).toInt(), segments.get(1).toInt())
        } else {
            val segments = value.substring(1).split("/")
            return Partner(segments.get(0), segments.get(1))
        }
    }
}
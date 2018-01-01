package net.mguenther.adventofcode.day20

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day20Solution {

    @Test
    fun should_show_solution_to_part_one() {
        println(solvePartOne(load()))
    }

    @Test
    fun should_show_solution_to_part_two() {
        println(solvePartTwo(load()))
    }

    private fun load(): List<Particle> {
        return Day20Solution::class.java
                .getResource("/day_20.input")
                .readText()
                .split("\n")
                .mapIndexed { index, s -> toParticle(index, s) }
    }

    private fun toParticle(id: Int, line: String): Particle {
        val vectors = line.split(", ")
        return Particle(id, parseVector(vectors[0]), parseVector(vectors[1]), parseVector(vectors[2]))
    }

    private fun parseVector(vector: String): Vector3 {
        val components = vector
                .trim()
                .substringAfter("<")
                .substringBefore(">")
                .split(",")
                .map { it.toLong() }
        return Vector3(components[0], components[1], components[2])
    }
}
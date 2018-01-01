package net.mguenther.adventofcode.day12

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day12Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(programsInRootComponent(loadNodes()))
    }

    @Test
    fun should_print_revised_solution_to_part_one() {
        val graph = UndirectedGraph("/day_12.input")
        val sizeOfCC = graph.sizeOfConnectedComponentFor(0)
        println(sizeOfCC)
    }

    @Test
    fun should_print_revised_solution_to_part_two() {
        val graph = UndirectedGraph("/day_12.input")
        val numberOfCC = graph.numberOfConnectedComponents()
        println(numberOfCC)
    }

    private fun loadNodes(): Map<Int, List<Int>> {

        val nodes = mutableMapOf<Int, List<Int>>()
        val lines = Day12Solution::class.java
                .getResource("/day_12.input")
                .readText()
                .split("\n")

        for (line in lines) {
            val tokens = line.split("<->")
            val from = tokens[0].trim().toInt()
            val to = tokens[1].trim().split(",").map { i -> i.trim().toInt() }
            nodes.put(from, to)
        }

        return nodes
    }
}
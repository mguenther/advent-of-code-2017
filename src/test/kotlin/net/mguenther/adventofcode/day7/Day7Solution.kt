package net.mguenther.adventofcode.day7

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day7Solution {

    @Test
    fun should_print_solution_to_part_one() {
        println(Graph(loadNodes()).rootNodes().get(0).id)
        println(findRoot(loadNodes()).id)
    }

    @Test
    fun should_print_solution_to_part_two() {
        val graph = Graph(loadNodes())
        println(graph.findOffBalance())
    }

    fun loadNodes(): List<Node> {
        return Day7Solution::class.java
                .getResource("/day_7.input")
                .readText()
                .split("\n")
                .map { toNode(it) }
    }

    private fun toNode(value: String): Node {
        if (value.contains("->")) {
            val segments = value.split("->")
            val id = segments[0].split(" ").get(0)
            val weight = segments[0].split(" ").get(1).replace("(", "").replace(")", "").toInt()
            val successors = segments[1].split(",").map { s -> s.trim() }
            return Node(id, weight, successors)
        } else {
            val segments = value.split(" ")
            val id = segments[0]
            val weight = segments[1].replace("(", "").replace(")", "").toInt()
            return Node(id, weight, emptyList())
        }
    }
}
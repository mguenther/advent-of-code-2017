package net.mguenther.adventofcode.day7

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.Arrays

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day7Test {

    @Test
    fun test() {
        val adjacencyList = Graph(loadNodes())
        println(adjacencyList.findOffBalance())
    }

    @Test
    fun rootNodes_should_contain_single_node_with_no_incoming_edges() {
        val adjacencyList = Graph(loadNodes())
        assertThat(adjacencyList.rootNodes().get(0).id, `is`("tknk"))
    }

    @Test
    fun weightOf_rootNode_is_correct() {
        val adjacencyList = Graph(loadNodes())
        println(adjacencyList.weightOf("tknk"))
    }

    @Test
    fun offBalance_rootNode_is_correct() {
        val adjacencyList = Graph(loadNodes())
        assertThat(adjacencyList.offBalance().get(0), `is`(8))
    }

    @Test
    fun should_yield_correct_root_node_for_minimal_example() {
        assertThat(findRoot(loadNodes()).id, `is`("tknk"))
    }

    fun loadNodes(): List<Node> {

        val nodes = Arrays.asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)")

        return nodes.map { toNode(it) }
    }

    fun toNode(value: String): Node {
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
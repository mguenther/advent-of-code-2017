package net.mguenther.adventofcode.day7

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

data class Node(val id: String, val weight: Int, val successors: List<String>)

class Graph(nodes: List<Node>) {

    private val nodes = nodes

    fun inDegreeOf(fromId: String): Int {

        return nodes
                .filter { node -> node.successors.contains(fromId) }
                .size
    }

    fun rootNodes(): List<Node> {

        return nodes
                .filter { node -> inDegreeOf(node.id) == 0 }
                .toList()
    }

    fun node(id: String): Node = nodes.first { node -> node.id.equals(id) }

    fun weightOf(id: String): Int {

        val node = node(id)

        return node.weight + node.successors.sumBy { s -> weightOf(s) }
    }

    fun offBalance(): List<Int> {

        return rootNodes().map { n -> n.successors.map { s -> weightOf(s) }.distinct().reduce { l, r -> Math.abs(l - r) } }
    }

    fun findOffBalance(): Int {

        val root = rootNodes().get(0)
        return findOffBalance(root, 0)
    }

    // findOffBalance is not a general solution, but tied to the given problem instance, since
    // we lose a bit of information with distinctBy and have to select the offending candidate
    // by either maxBy or minBy. in case of the given problem, maxBy yields the correct solution
    // but this does not have to be the case
    private fun findOffBalance(root: Node, off: Int): Int {

        val candidates = root.successors.map { s -> Pair(s, weightOf(s)) }.distinctBy { p -> p.second }

        if (candidates.size == 1) {
            // if there is only one candidate left, we found the offending node
            // and have to subtract the off value from its weight
            return root.weight - off
        } else {
            // we haven't found the culprit yet, so we have to check the candidate
            // that has the differing value and go one layer deeper
            val newOff = candidates.map { c -> c.second }.reduce { l, r -> Math.abs(l - r) }
            return findOffBalance(node(candidates.maxBy { p -> p.second }!!.first), newOff)
        }
    }
}


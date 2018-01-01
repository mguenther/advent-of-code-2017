package net.mguenther.adventofcode.day12

import net.mguenther.adventofcode.array2dOfBoolean

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

data class Node(val id: String, val successors: Map<Int, List<Int>>)

fun programsInRootComponent(nodes: Map<Int, List<Int>>): Int {

    val adjacencyMatrix = computeAdjacencyMatrix(nodes)
    var transitiveClosure = adjacencyMatrix.clone()

    for (k in 0 until adjacencyMatrix.size) {
        val nextTransitiveClosure = array2dOfBoolean(nodes.size, nodes.size)
        for (i in 0 until adjacencyMatrix.size) {
            for (j in 0 until adjacencyMatrix.size) {
                nextTransitiveClosure[i][j] = transitiveClosure[i][j] || transitiveClosure[i][k] && transitiveClosure[k][j]
            }
        }
        transitiveClosure = nextTransitiveClosure
    }

    return transitiveClosure[0]
            .asList()
            .map { connected -> if (connected) 1 else 0 }
            .sum()
}

private fun computeAdjacencyMatrix(nodes: Map<Int, List<Int>>): Array<BooleanArray> {
    val adjacencyMatrix = array2dOfBoolean(nodes.size, nodes.size)
    for (from in nodes.keys) {
        for (to in nodes.get(from)!!) {
            adjacencyMatrix[from][to] = true
            adjacencyMatrix[to][from] = true
        }
    }
    return adjacencyMatrix
}

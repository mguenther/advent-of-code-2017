package net.mguenther.adventofcode.day7

import net.mguenther.adventofcode.array2dOfBoolean

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun findRoot(nodes: List<Node>): Node {
    val adjacencyMatrix = array2dOfBoolean(nodes.size, nodes.size)
    val nameToIndex = mutableMapOf<String, Int>()
    for (i in 0 until nodes.size) nameToIndex.put(nodes.get(i).id, i)
    for (i in 0 until nodes.size) {
        val node = nodes.get(i)
        for (successor in node.successors) {
            adjacencyMatrix[nameToIndex.getValue(node.id)][nameToIndex.getValue(successor)] = true
        }
    }
    // now we simply look up the column that has no incoming connections to identify the root node
    var index = -1
    for (col in 0 until adjacencyMatrix.size) {
        var noIncomingConnection = true
        for (row in 0 until adjacencyMatrix[col].size) {
            noIncomingConnection = noIncomingConnection && !adjacencyMatrix[row][col]
        }
        if (noIncomingConnection) {
            index = col
            break
        }
    }
    return nodes.get(index)
}

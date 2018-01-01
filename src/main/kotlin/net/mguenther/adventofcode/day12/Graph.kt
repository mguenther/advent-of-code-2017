package net.mguenther.adventofcode.day12

import java.util.Optional
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class UndirectedGraph(val resourceFile: String) {

    private val adjacencyList: Map<Int, List<Int>> = load()

    fun sizeOfConnectedComponentFor(root: Int): Int {

        val Q: Queue<Int> = LinkedBlockingQueue()
        val visited: MutableSet<Int> = mutableSetOf()

        Q.add(root)

        while (Q.isNotEmpty()) {
            val node = Q.poll()
            visited.add(node)
            adjacencyList.getOrDefault(node, emptyList())
                    .filterNot { visited.contains(it) }
                    .forEach { Q.offer(it) }
        }

        return visited.size
    }

    fun numberOfConnectedComponents(): Int {

        val Q: Queue<Int> = LinkedBlockingQueue()
        val visited: MutableSet<Int> = mutableSetOf()
        var scc = 0

        firstUnvisited(visited).ifPresent { Q.add(it); visited.add(it); scc++ }

        while (Q.isNotEmpty()) {
            val node = Q.poll()
            adjacencyList.getOrDefault(node, emptyList())
                    .filterNot { visited.contains(it) }
                    .forEach { Q.offer(it); visited.add(it) }
            if (Q.isEmpty()) firstUnvisited(visited).ifPresent { Q.add(it); visited.add(it); scc++ }
        }

        return scc
    }

    private fun firstUnvisited(visited: Set<Int>): Optional<Int> {
        return adjacencyList.keys.stream().filter { n -> !visited.contains(n) }.findFirst()
    }

    private fun load(): Map<Int, List<Int>> {

        val adjacencyList: MutableMap<Int, List<Int>> = mutableMapOf()
        val lines = UndirectedGraph::class.java
                .getResource(resourceFile)
                .readText()
                .split("\n")

        for (line in lines) {
            val tokens = line.split("<->")
            val from = tokens[0].trim().toInt()
            val neighbours = tokens[1].trim().split(",").map { i -> i.trim().toInt() }
            for (neighbour in neighbours) {
                adjacencyList.put(from, adjacencyList.getOrDefault(from, emptyList()) + neighbour)
                adjacencyList.put(neighbour, adjacencyList.getOrDefault(neighbour, emptyList()) + from)
            }
        }

        return adjacencyList
    }
}

package net.mguenther.adventofcode.day14

import net.mguenther.adventofcode.day10.KnotHash
import java.math.BigInteger

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Day14Additive(private val prefix: String) {

    val grid: List<IntArray> = IntRange(0, 127)
            .map { "$prefix-$it" }
            .map { KnotHash(256).checksum(it) }
            .map { BigInteger(it, 16).toString(2).padStart(128, '0') }
            .map { s -> s.map { it-'0' } }
            .map { it.toIntArray() }

    var groups = 2

    fun groups(): Int {

        groups = 2 // set to initial value

        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, col -> if (col < 2 && col > 0) {
                groups++
                mark(x, y)
            }
            }
        }

        return groups - 2 // minus the initial 2
    }

    private fun neighbours(row: Int, col: Int): List<Pair<Int, Int>> =
            listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
                    .map { Pair(it.first + row, it.second + col) }
                    .filter { it.first in 0..127 }
                    .filter { it.second in 0..127 }


    private fun mark(row: Int, col: Int) {
        if (grid[row][col] == 1) {
            grid[row][col] = groups
            neighbours(row, col).forEach { mark(it.first, it.second) }
        }
    }
}

class Day14Reductionism(private val prefix: String) {

    val grid: List<IntArray> = IntRange(0, 127)
            .map { "$prefix-$it" }
            .map { KnotHash(256).checksum(it) }
            .map { BigInteger(it, 16).toString(2).padStart(128, '0') }
            .map { s -> s.map { it-'0' } }
            .map { it.toIntArray() }

    var groups = 0

    fun groups(): Int {

        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, col -> if (col == 1) {
                groups++
                mark(x, y)
            }
            }
        }

        return groups
    }

    private fun neighbours(row: Int, col: Int): List<Pair<Int, Int>> =
            listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
                    .map { Pair(it.first + row, it.second + col) }
                    .filter { it.first in 0..127 }
                    .filter { it.second in 0..127 }


    private fun mark(row: Int, col: Int) {
        if (grid[row][col] == 1) {
            grid[row][col] = 0
            neighbours(row, col).forEach { mark(it.first, it.second) }
        }
    }
}

fun defrag(prefix: String): Int {

    return IntRange(0, 127)
            .map { "$prefix-$it" }
            .map { KnotHash(256).checksum(it) }
            .map { BigInteger(it, 16).toString(2).padStart(128, '0') }
            .map { it.count { c -> c.equals('1') } }
            .sum()

}

fun main(args: Array<String>) {
    println(defrag("ugkiagan"))
    println(Day14Additive("ugkiagan").groups())
    println(Day14Reductionism("ugkiagan").groups())
}
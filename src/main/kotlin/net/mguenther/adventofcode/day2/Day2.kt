package net.mguenther.adventofcode.day2

import java.io.File

/**
 * See http://adventofcode.com/2017/day/2
 *
 * @author Markus Günther (markus.guenther@gmail.com)
 */
fun minmax(columns: List<String>): Pair<Int, Int> {
    val numbers = columns.map { it.toInt() }
    return Pair(numbers.min() ?: 0, numbers.max() ?: 0)
}

fun diff(minmax: Pair<Int, Int>): Int {
    return minmax.second - minmax.first
}

fun evenlyDivides(columns: List<String>): Int {
    var result: Int = 0
    val numbers = columns.map { it.toInt() }
    for (i in 0 until numbers.size) {
        for (j in 0 until numbers.size) {
            if (i == j) continue
            val l = numbers.get(i)
            val r = numbers.get(j)
            if (l.rem(r) == 0) {
                result = l / r
                // there is only one such pair per row
                break
            }
        }
    }
    return result
}

fun main(args: Array<String>) {
    val rows = mutableListOf<String>()
    File("src/day2.input").useLines { lines -> lines.forEach { rows.add(it) }}
    println("Checksum is: " + rows
            .stream()
            .map { row -> minmax(row.split(" ")) }
            .map { minmax -> diff(minmax) }
            .reduce { t: Int, u: Int -> t + u })
    println("Checksum is: " + rows
            .stream()
            .map { row -> evenlyDivides(row.split(" ")) }
            .reduce { t: Int, u: Int -> t + u })
}
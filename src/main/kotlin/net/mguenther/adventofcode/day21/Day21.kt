package net.mguenther.adventofcode.day21

import kotlin.math.sqrt

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

data class Pattern(val grid: List<List<Char>>) {

    constructor(input: String) : this(input.split("/").map { it.toList() })

    private fun flip(): Pattern = Pattern(grid.map { it.reversed() })

    private fun rotate(): Pattern =
            Pattern(
                    (0 until grid.size).map { row ->
                        (0 until grid.size).map { column ->
                            grid[grid.size - 1 - column][row]
                        }
                    }
            )

    fun isoforms(): List<Pattern> {
        val rotations = (0 until 3).fold(listOf(this)) { acc, _ -> acc + acc.last().rotate() }
        val flips = rotations.map { it.flip() }
        return rotations + flips
    }

    fun split(): List<Pattern> {

        if (grid.size == 2 || grid.size == 3) return listOf(this)
        else {
            val sizeOfSegment = when {
                grid.size % 2 == 0 -> 2
                else -> 3
            }
            return split(sizeOfSegment)
        }
    }

    private fun split(sizeOfSegment: Int): List<Pattern> =
            (0 until grid.size / sizeOfSegment).flatMap { rowSegment ->
                (0 until grid.size / sizeOfSegment).map { colSegment ->
                    Pattern(
                            (0 until sizeOfSegment).map { rowInSegment ->
                                (0 until sizeOfSegment).map { colInSegment ->
                                    grid[rowSegment * sizeOfSegment + rowInSegment][colSegment * sizeOfSegment + colInSegment]
                                }
                            })
                }
            }

    private fun nextTo(that: Pattern): Pattern = Pattern(grid.mapIndexed { i, row -> row + that.grid[i] })
    private fun over(that: Pattern): Pattern = Pattern(grid + that.grid)

    companion object {
        fun combine(patterns: List<Pattern>): Pattern {
            if (patterns.size == 1) {
                return patterns.get(0)
            } else {
                val chunks = sqrt(patterns.size.toFloat()).toInt()
                return patterns
                        .chunked(chunks).map { it.reduce { l, r -> l.nextTo(r) } }
                        .chunked(chunks).map { it.reduce { l, r -> l.over(r) } }
                        .first()
            }
        }

    }

    override fun toString(): String {
        return grid.joinToString("/") { it.joinToString("") }
    }
}

fun solve(rules: Map<Pattern, Pattern>, initial: Pattern = Pattern(".#./..#/###"), iterations: Int = 4) {

    var pattern: Pattern = initial.split().map { rules[it] }.get(0)!!

    repeat(iterations) {
        val newPatterns = pattern.split().map { rules[it]!! }
        pattern = Pattern.combine(newPatterns)
    }

    println(pattern.toString().count { c -> c == '#' })
}

fun readRules(resourceFile: String): Map<Pattern, Pattern> {
    return Thread::class.java.getResource(resourceFile)
            .readText()
            .split("\n")
            .map { toPatterns(it) }
            .reduceRight { l, r -> l + r }
}

private fun toPatterns(line: String): Map<Pattern, Pattern> {
    val tokens = line.split(" => ")
    val ruleBlueprint = Pattern(tokens[0])
    val replacement = Pattern(tokens[1])
    return ruleBlueprint.isoforms().map { Pair(it, replacement) }.toMap()
}

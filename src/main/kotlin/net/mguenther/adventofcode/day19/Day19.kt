package net.mguenther.adventofcode.day19

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Tubular(private val grid: List<CharArray>) {

    private val directions: List<Direction> = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    tailrec fun walk(currentPosition: Position,
             currentDirection: Direction,
             collected: List<Char> = emptyList(),
             steps: Int = 0): String {

        if (isExit(currentPosition)) {
            println("Walked all the way to the exit. Took $steps steps.")
            return collected.joinToString("")
        } else {
            when (at(currentPosition)) {
                in 'A'..'Z' -> {
                    val letter = at(currentPosition)
                    println("Found letter $letter at position $currentPosition")
                    return walk(currentPosition.apply(currentDirection), currentDirection, collected + letter, steps.inc())
                }
                '+' -> {
                    val newDirection = nextTurn(currentPosition, currentDirection)
                    println("Found myself on a crossroad at $currentPosition. Turning to $newDirection")
                    return walk(currentPosition.apply(newDirection), newDirection, collected, steps.inc())
                }
                else -> {
                    println("Continuing along direction $currentDirection from $currentPosition")
                    return walk(currentPosition.apply(currentDirection), currentDirection, collected, steps.inc())
                }
            }
        }
    }

    private fun at(pos: Position) = grid[pos.y][pos.x]

    private fun nextTurn(currentPosition: Position, currentDirection: Direction): Direction =
        directions
                .filter { currentDirection != it }
                .filter { currentDirection.negate() != it }
                .first { at(currentPosition.apply(it)) != ' ' }

    private fun isExit(pos: Position) = at(pos) == ' '
}

data class Position(val x: Int, val y: Int) {
    fun apply(direction: Direction): Position = Position(x + direction.first, y + direction.second)
}

typealias Direction = Pair<Int, Int>

private fun Pair<Int, Int>.negate(): Pair<Int, Int> = Pair(-this.first, -this.second)
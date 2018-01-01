package net.mguenther.adventofcode.day3

import net.mguenther.adventofcode.array2dOfInt

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class SquareFiller(fillUntilLargerThan: Int) {

    private val fillUntilLargerThan = fillUntilLargerThan
    private val internalArray = array2dOfInt(11, 11)
    private val neighbourVectors: List<Pair<Int, Int>> = listOf(Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(1, -1), Pair(0, -1), Pair(-1, -1), Pair(-1, 0), Pair(-1, 1))
    private var position: Pair<Int, Int> = Pair(5, 5)
    private var layer = 1

    init {
        internalArray[position.first][position.second] = 1
    }

    fun fill() {
        while (!fillLayer()) {}
    }

    private fun fillLayer(): Boolean {
        for (move in moveCommands(layer)) {
            for (i in 0 until move.times) {
                position = Pair(position.first + move.direction.first, position.second + move.direction.second)
                val sum = sumOfNeighbours(position)
                internalArray[position.first][position.second] = sum
                if (sum > fillUntilLargerThan) {
                    println(sum)
                    return true
                }
            }
        }
        layer++
        return false
    }

    private fun moveCommands(layer: Int): List<MoveSequence> {
        return listOf(
                MoveSequence(moveRight, 1),
                MoveSequence(moveUp, 2 * layer - 1),
                MoveSequence(moveLeft, 2 * layer),
                MoveSequence(moveDown, 2 * layer),
                MoveSequence(moveRight, 2 * layer))
    }

    fun sumOfNeighbours(position: Pair<Int, Int>): Int {

        return neighbourVectors
                .map { (first, second) -> Pair(position.first + first, position.second + second) }
                .filterNot { neighbour -> outOfBounds(neighbour) }
                .map { (first, second) -> internalArray[first][second] }
                .sum()
    }

    private fun outOfBounds(position: Pair<Int, Int>): Boolean {
        val y = position.first
        val x = position.second
        return y < 0 || x < 0 || y >= internalArray.size || x >= internalArray[0].size
    }
}

data class MoveSequence(val direction: Pair<Int, Int>, val times: Int)

val moveRight = Pair(0,1)
val moveUp = Pair(-1, 0)
val moveLeft = Pair(0, -1)
val moveDown = Pair(1, 0)
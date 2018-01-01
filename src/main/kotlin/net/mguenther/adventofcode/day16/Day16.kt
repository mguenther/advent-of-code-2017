package net.mguenther.adventofcode.day16

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

val alphabet: Array<String> = arrayOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
        "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

class Promenade(size: Int) {

    private val numberOfPrograms: Int = size.coerceAtMost(25)
    private var positions: Array<String> = emptyArray()

    init {
        positions = alphabet.copyOfRange(0, numberOfPrograms)
    }

    private fun spin(size: Int) {

        val newPositions: Array<String> = Array(numberOfPrograms, { "" })

        for (i in 0 until numberOfPrograms) {
            val newIndex = (i + size).rem(numberOfPrograms)
            newPositions[newIndex] = positions[i]
        }

        positions = newPositions
    }

    private fun exchange(positionA: Int, positionB: Int) {
        val swap = positions[positionA]
        positions[positionA] = positions[positionB]
        positions[positionB] = swap
    }

    private fun partner(programA: String, programB: String) {
        var positionA: Int = -1;
        var positionB: Int = -1;
        for (i in 0 until positions.size) {
            if (positions[i].equals(programA)) positionA = i
            if (positions[i].equals(programB)) positionB = i
            if (positionA != -1 && positionB != -1) break
        }
        exchange(positionA, positionB)
    }

    fun dance(moves: List<Move>) {
        dance(moves, 1)
    }

    fun dance(moves: List<Move>, times: Int) {
        repeat(times, {
            time -> run {
                val percentage = ((time.toFloat() / times.toFloat()) * 100).toInt()
                println("Applying dance #" + time + " (" + percentage + "%)")
                moves.forEach(this::apply)
            }
        })
    }

    private fun apply(move: Move) = when(move) {
        is Spin -> spin(move.size)
        is Exchange -> exchange(move.positionA, move.positionB)
        is Partner -> partner(move.programA, move.programB)
    }

    override fun toString(): String {
        return positions.reduce { l: String, r: String -> l + r }
    }
}

sealed class Move
data class Spin(val size: Int) : Move()
data class Exchange(val positionA: Int, val positionB: Int) : Move()
data class Partner(val programA: String, val programB: String) : Move()
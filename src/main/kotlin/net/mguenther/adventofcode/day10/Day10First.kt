package net.mguenther.adventofcode.day10

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class KnotHashFirst(val size: Int) {

    private var knot: IntArray = IntArray(size)
    private var offset: Int = 0
    private var skip: Int = 0

    init {
        for (i in 0 until knot.size) knot[i] = i
    }

    fun knot(lengths: IntArray): Int {
        lengths.forEach { length -> knot(length) }
        return knot[0] * knot[1]
    }

    private fun knot(length: Int) {

        if (length > knot.size) return

        reverse(offset, tailOffset(offset, length-1), length)

        offset = nextOffset(offset, length)
        increaseSkip()
    }

    private fun reverse(head: Int, tail: Int, length: Int) {
        val adjustedKnot = knot.copyOf()
        var i = 0
        while (i < length) {
            adjustedKnot[copyToOffset(tail, i)] = knot[(head + i).rem(knot.size)]
            i++
        }
        knot = adjustedKnot
    }

    private fun tailOffset(offset: Int, length: Int): Int = (offset + length).rem(knot.size)

    private fun nextOffset(offset: Int, length: Int): Int = (offset + length + skip).rem(knot.size)

    private fun copyToOffset(tail: Int, i: Int): Int = when {
        (tail - i) < 0 -> { ((knot.size) - Math.abs(tail-i)).rem(knot.size)}
        else           -> { Math.abs(tail-i).rem(knot.size) }
    }
    private fun increaseSkip() { skip++ }

    override fun toString(): String {
        return knot.contentToString()
    }
}

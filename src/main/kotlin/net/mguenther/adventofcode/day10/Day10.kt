package net.mguenther.adventofcode.day10

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class KnotHash(val size: Int) {

    private val suffix: IntArray = intArrayOf(17, 31, 73, 47, 23)
    private var knot: IntArray = IntArray(size)
    private var offset: Int = 0
    private var skip: Int = 0

    init {
        for (i in 0 until knot.size) knot[i] = i
    }

    fun checksum(input: String): String {
        return checksum(input, 64)
    }

    fun checksum(input: String, times: Int): String {
        val inputWithSuffix = combine(input.toCharArray().map { c -> c.toInt() }.toIntArray(), suffix)
        return knot(inputWithSuffix, times)
    }

    private fun combine(left: IntArray, right: IntArray): IntArray {
        val combinedLength = left.size + right.size
        val combinedArray = IntArray(combinedLength)
        var pos = 0
        for (element in left) {
            combinedArray[pos] = element
            pos++
        }
        for (element in right) {
            combinedArray[pos] = element
            pos++
        }
        return combinedArray
    }

    private fun knot(lengths: IntArray, times: Int): String {
        for (i in 0 until times) lengths.forEach { length -> knot(length) }
        return encode(knot)
    }

    private fun encode(lengths: IntArray): String {
        var hash = ""
        for (i in 0 until 16) {
            var sparseNumber = 0
            for (j in 0 until 16) {
                sparseNumber = sparseNumber.xor(lengths[i * 16 + j])
            }
            var hex = sparseNumber.toString(16)
            if (hex.length < 2) hex = "0" + hex
            hash += hex
        }
        return hash
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
        (tail - i) < 0 -> (knot.size - Math.abs(tail-i)).rem(knot.size)
        else           -> Math.abs(tail-i).rem(knot.size)
    }

    private fun increaseSkip() { skip++ }

    override fun toString(): String {
        return knot.contentToString()
    }
}

package net.mguenther.adventofcode.day5

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
fun maze(offsets: IntArray): Int {
    var currentOffset = 0
    var steps = 0
    while (!outOfBounds(currentOffset, offsets.size)) {
        val jumpToOffset = currentOffset + offsets[currentOffset]
        offsets[currentOffset]++
        currentOffset = jumpToOffset
        steps++
        if (outOfBounds(currentOffset, offsets.size)) {
            break
        }
    }
    return steps
}

fun strangerMaze(offsets: IntArray): Int {
    var currentOffset = 0
    var steps = 0
    while (!outOfBounds(currentOffset, offsets.size)) {
        val jumpToOffset = currentOffset + offsets[currentOffset]
        if (offsets[currentOffset] >= 3)
            offsets[currentOffset]--
        else
            offsets[currentOffset]++
        currentOffset = jumpToOffset
        steps++
        if (outOfBounds(currentOffset, offsets.size)) {
            break
        }
    }
    return steps
}

fun outOfBounds(offset: Int, size: Int) = offset < 0 || offset >= size

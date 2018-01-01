package net.mguenther.adventofcode.day13

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun severityOfTrip(layers: List<Layer>): Int = severityOfTrip(layers, 0)
fun severityOfTrip(layers: List<Layer>, delay: Int): Int {
    return layers.filter { it.caught(delay) }.sumBy { it.severity() }
}

data class Layer(val depth: Int, val range: Int) {
    fun caught(tick: Int) = ((tick + depth) % ((range - 1) * 2)) == 0
    fun severity() = depth * range
}

package net.mguenther.adventofcode.day3

import java.lang.Math.abs
import java.lang.Math.ceil
import java.lang.Math.pow
import java.lang.Math.sqrt

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
fun layer(i: Int): Int {
    return (ceil((sqrt(i.toDouble()) - 1.0) / 2.0)).toInt()
}

fun start(i: Int): Int {
    return pow((2 * layer(i) - 1).toDouble(), 2.0).toInt()
}

fun segment(i: Int): Int {
    return ceil((i - start(i)).toDouble() / (layer(i) * 2).toDouble()).toInt()
}

fun corner(i: Int): Int {
    return segment(i) * layer(i) * 2 + start(i)
}

fun distance(i: Int): Int {
    return layer(i) + abs(corner(i) - layer(i) - i)
}

package net.mguenther.adventofcode.day17

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun spinlock(seed: Int, end: Int = 9): Int {
    val spinlock = mutableListOf(0)
    var pos = 0
    for (i in 1 .. end) {
        pos = ((pos + seed) % i) + 1
        spinlock.add(pos, i)
    }
    return spinlock[++pos % spinlock.size]
}

fun angrySpinlock(seed: Int, end: Int = 9): Int {
    var nextToZero = 0
    var pos = 0
    for (i in 1 .. end) {
        pos = ((pos + seed) % i) + 1 // this can never be zero, so the first value '0' stays in place the whole time
        if (pos == 1) nextToZero = i
    }
    return nextToZero
}

fun main(args: Array<String>) {
    println(spinlock(366, 2017))
    println(angrySpinlock(366, 50_000_000))
}
package net.mguenther.adventofcode.day15

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun generator(seed: Long, factor: Long, predicate: (i: Short) -> Boolean = { _ -> true }, divisor: Long = 2147483647): Sequence<Short> =
    generateSequence(next(seed, factor, divisor)) { last -> next(last, factor, divisor) }
        .map { it.toShort() }
        .filter(predicate)

fun next(last: Long, factor: Long, divisor: Long) = (last * factor) % divisor

fun main(args: Array<String>) {

    val genA = generator(634, 16807, { it % 4 == 0})
    val genB = generator(301, 48271, { it % 8 == 0})

    println(genA
            .zip(genB)
            .take(5_000_000)
            .map { it.first == it.second }
            .sumBy { if (it) 1 else 0 })
}
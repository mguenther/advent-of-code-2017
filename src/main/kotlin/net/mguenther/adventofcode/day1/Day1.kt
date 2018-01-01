package net.mguenther.adventofcode.day1

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
fun captcha(digits: List<Int>, nextFn: (i: Int, length: Int) -> Int): Int {
    val length = digits.size
    var sum = 0
    for (i in 0 until length) {
        val next = nextFn.invoke(i, length)
        if (digits[i] == digits[next])
            sum += digits[i]
    }
    return sum
}

fun successor(i: Int, length: Int): Int {
    return (i + 1).rem(length)
}

fun halfway(i: Int, length: Int): Int {
    return (i + length / 2).rem(length)
}

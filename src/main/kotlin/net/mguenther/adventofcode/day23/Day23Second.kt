package net.mguenther.adventofcode.day23

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun refactoredAsm(): Int {

    var b = 93
    var c = b
    var h = 0

    b = b * 100 + 100000
    c = b + 17000

    while (true) {
        var f = 1
        var d = 2

        while (d < b) {
            if (b % d == 0) {
                f = 0
                break
            }
            d++
        }

        if (f == 0) h++
        if (b == c) return h

        b += 17
    }
}

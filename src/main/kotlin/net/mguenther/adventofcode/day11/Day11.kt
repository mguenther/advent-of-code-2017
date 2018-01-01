package net.mguenther.adventofcode.day11

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

fun hexDistance(moves: List<String>) {

    var x = 0
    var y = 0
    var z = 0

    val distances: MutableList<Int> = mutableListOf()

    for (move in moves) {

        when (move) {
            "n"  -> { y += 1; z -= 1 }
            "s"  -> { y -= 1; z += 1 }
            "ne" -> { x += 1; z -= 1 }
            "sw" -> { x -= 1; z += 1 }
            "nw" -> { x -= 1; y += 1 }
            "se" -> { x += 1; y -= 1 }
        }

        distances.add(hexDistance(x, y, z))
    }

    println(hexDistance(x, y, z))
    println(distances.max())
}

private fun hexDistance(x: Int, y: Int, z: Int) = (Math.abs(x) + Math.abs(y) + Math.abs(z)).shr(1)
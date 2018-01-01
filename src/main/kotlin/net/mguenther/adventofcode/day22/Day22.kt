package net.mguenther.adventofcode.day22

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Sporifica(cluster: Array<CharArray>) {

    private var cluster: Array<CharArray> = cluster
    private var position: Pair<Int, Int> = Pair(cluster.size / 2, cluster.size / 2) // y-x
    private var facing: Direction = North
    private var infections: Int = 0

    fun burst(times: Int) = repeat(times, { i -> burst() })

    fun burst() {

        if (isInfected()) {
            facing = facing.right()
            flag()
        } else if (isWeakened()) {
            infect()
        } else if (isFlagged()) {
            facing = facing.reverse()
            clean()
        } else {
            facing = facing.left()
            weaken()
        }

        move()
    }

    private fun isInfected(): Boolean = cluster[position.first][position.second] == '#'
    private fun isWeakened(): Boolean = cluster[position.first][position.second] == 'w'
    private fun isFlagged(): Boolean = cluster[position.first][position.second] == 'f'
    private fun clean() { cluster[position.first][position.second] = '.' }
    private fun weaken() { cluster[position.first][position.second] = 'w' }
    private fun flag() { cluster[position.first][position.second] = 'f' }
    private fun infect() {
        cluster[position.first][position.second] = '#'
        infections++
    }
    private fun move() {
        position = Pair(position.first + facing.vector().first, position.second + facing.vector().second)
    }

    fun infections(): Int = infections

    override fun toString(): String {
        var str = ""
        for (y in 0 until cluster.size) {
            for (x in 0 until cluster[y].size) {
                if (Pair(y,x).equals(position))
                    str += "o"
                else
                    str += cluster[y][x]
            }
            str += "\n"
        }
        return str
    }
}

sealed class Direction {
    abstract fun left(): Direction
    abstract fun right(): Direction
    abstract fun reverse(): Direction
    abstract fun vector(): Pair<Int, Int>
}

object North : Direction() {
    override fun left(): Direction = West
    override fun right(): Direction = East
    override fun reverse(): Direction = South
    override fun vector(): Pair<Int, Int> = Pair(-1, 0)
}

object South: Direction() {
    override fun left(): Direction = East
    override fun right(): Direction = West
    override fun reverse(): Direction = North
    override fun vector(): Pair<Int, Int> = Pair(1, 0)
}

object East : Direction() {
    override fun left(): Direction = North
    override fun right(): Direction = South
    override fun reverse(): Direction = West
    override fun vector(): Pair<Int, Int> = Pair(0, 1)
}

object West : Direction() {
    override fun left(): Direction = South
    override fun right(): Direction = North
    override fun reverse(): Direction = East
    override fun vector(): Pair<Int, Int> = Pair(0, -1)
}
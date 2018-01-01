package net.mguenther.adventofcode.day18

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class PartOne {

    private val registers: MutableMap<Register, Long> = mutableMapOf()
    private var mostRecentlyPlayed: Long = 0L
    private var recovered: Long = 0L
    private var pc = 0

    fun execute(`is`: List<Instruction>): Long {
        while (pc < `is`.size && pc >= 0 && recovered == 0L) {
            execute(`is`.get(pc))
        }
        return recovered
    }

    private fun execute(i: Instruction) {
        var jumpBy = 1
        when (i.command) {
            "snd" -> mostRecentlyPlayed = read(i.target)
            "set" -> resolve(i.param).let { registers.put(i.target, it) }
            "add" -> read(i.target).plus(resolve(i.param)).let { write(i.target, it) }
            "mul" -> read(i.target).times(resolve(i.param)).let { write(i.target, it) }
            "mod" -> read(i.target).rem(resolve(i.param)).let { write(i.target, it) }
            "rcv" -> {
                val value = read(i.target)
                if (value != 0L) {
                    recovered = mostRecentlyPlayed
                }
            }
            else -> if (read(i.target) > 0) jumpBy = resolve(i.param).toInt()
        }
        pc += jumpBy
    }

    private fun read(r: Register): Long = registers.getOrDefault(r, 0)
    private fun write(r: Register, value: Long) = registers.put(r, value)
    private fun resolve(value: String) =
            if (isDigit(value)) value.toLong()
            else read(value)
    private fun isDigit(p: String): Boolean = p.isNotEmpty() && p.toCharArray()[0].isDigit() || p.toCharArray()[0] == '-'
}

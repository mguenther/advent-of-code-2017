package net.mguenther.adventofcode.day23

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class CoProcessor {

    private val registers: MutableMap<Register, Int> = mutableMapOf(Pair("a", 0), Pair("b", 0), Pair("c", 0), Pair("d", 0), Pair("e", 0), Pair("f", 0), Pair("g", 0), Pair("h", 0))

    private var mul: Int = 0

    fun process(instructions: List<Instruction>) {

        var ic = 0

        while (ic < instructions.size) {
            val next = process(instructions[ic])
            ic += next
        }
    }

    fun process(i: Instruction): Int {
        var next = 1
        when (i) {
            is SetByRef -> store(i.x, deref(i.y))
            is SetByVal -> store(i.x, i.y)
            is SubByRef -> store(i.x, deref(i.x) - deref(i.y))
            is SubByVal -> store(i.x, deref(i.x) - i.y)
            is MulByRef -> {
                store(i.x, deref(i.x) * deref(i.y))
                mul++
            }
            is MulByVal -> {
                store(i.x, deref(i.x) * i.y)
                mul++
            }
            is JnzByRef -> if (deref(i.x) != 0) next = i.y else 1
            is JnzByVal -> if (i.x != 0) next = i.y else 1
        }
        return next
    }

    fun deref(r : Register): Int = registers.getOrDefault(r, 0)
    private fun store(r : Register, value: Int) = registers.put(r, value)

    fun invocationsOfMul(): Int = mul
}

sealed class Instruction

data class SetByRef(val x: Register, val y: Register) : Instruction()
data class SetByVal(val x: Register, val y: Int) : Instruction()
data class SubByRef(val x: Register, val y: Register) : Instruction()
data class SubByVal(val x: Register, val y: Int) : Instruction()
data class MulByRef(val x: Register, val y: Register) : Instruction()
data class MulByVal(val x: Register, val y: Int): Instruction()
data class JnzByRef(val x: Register, val y: Int) : Instruction()
data class JnzByVal(val x: Int, val y: Int) : Instruction()

typealias Register = String
